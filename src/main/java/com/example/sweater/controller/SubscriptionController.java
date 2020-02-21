package com.example.sweater.controller;

import com.example.sweater.model.User;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

@Controller
public class SubscriptionController {

    @Autowired
    private UserService userService;

    @GetMapping("/subscriptions")
    public String getSignUp(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ){
        // todo test this
//        Optional<User> user = userService.findById(1L);
//        user.ifPresent(u -> model.put("subscriptions", u.getSubscriptions()));

//        model.put("subscriptions", user.getSubscriptions());
        model.put("username", user.getUsername());
        model.put("subscriptions", new HashSet());
        return "subscriptions/subscriptions";
    }
}
