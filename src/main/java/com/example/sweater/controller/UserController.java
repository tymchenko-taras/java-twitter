package com.example.sweater.controller;

import com.example.sweater.model.Role;
import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getSignUp(Map<String, Object> model) {
        model.put("users", userService.findAll());
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
        userService.saveUser(user, username, form);
        return "redirect:/users";
    }

    @GetMapping("user/profile")
    public String getProfile(
            Map<String, Object> model,
            @AuthenticationPrincipal User user
    ){
        model.put("user", user);
        return "user/profile";
    }

    @PostMapping("user/profile")
    public String postProfile(
            @RequestParam String email,
            @RequestParam String password,
            Map<String, Object> model,
            @AuthenticationPrincipal User user
    ){

        userService.updateProfile(user, email, password);
        return "redirect:/user/profile";
    }
}
