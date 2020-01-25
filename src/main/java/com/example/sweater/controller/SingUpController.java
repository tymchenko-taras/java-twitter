package com.example.sweater.controller;

import com.example.sweater.model.Role;
import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class SingUpController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String getSignUp(){
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignUp(
            @RequestParam String username,
            @RequestParam String password,
            Map<String, Object> model
    ){

        User user = userRepository.findByUsername(username);
        if(user != null){
            model.put("message", "username already exists");
            return "signup";
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setActive(true);
        Set<Role> a = new HashSet<Role>();
        a.add(Role.USER);
        user.setRoles(a);
        userRepository.save(user);

        return "redirect:/login";
    }
}
