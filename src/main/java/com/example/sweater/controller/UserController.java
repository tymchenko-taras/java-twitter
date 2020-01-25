package com.example.sweater.controller;

import com.example.sweater.model.Role;
import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String getSignUp(Map<String, Object> model) {
        model.put("users", userRepository.findAll());
        return "user/list";
    }

    @GetMapping("/user/{user}")
    public String getUserEditForm(
            @PathVariable User user,
            Map<String, Object> model
    ) {
        model.put("user", user);
        model.put("roles", Role.values());
        return "user/edit";
    }
    @PostMapping("/user/{user}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String saveUserForm(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @PathVariable User user,
            Map<String, Object> model
    ) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        user.setUsername(username);
        userRepository.save(user);
        return "redirect:/users";
    }
}
