package com.example.sweater.controller;

import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import com.example.sweater.service.CaptchaService;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SingUpController {
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String getSignUp(
            Map<String, Object> model
    ){
        model.put("sitekey", captchaService.getSiteKey());
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignUp(
            @RequestParam(name = "g-recaptcha-response") String captchaResponse,
            User user,
            Map<String, Object> model
    ) {

       if(!captchaService.isSuccess(captchaResponse)){
            model.put("message", "Wrong Captcha");
            return this.getSignUp(model);
        }
        if(!userService.addUser(user)){
            model.put("message", "User exists");
            return "signup";
        }
        if(!userService.sendConfirmation(user)){
            model.put("message", "Unable to send confirmation");
            return "signup";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{hash}")
    public String activate(
            @PathVariable String hash,
            Map<String, Object> model
    ){
        User user = userRepository.findByActivationHash(hash);
        if(user == null){
            model.put("messsage", "Couldnt find user");
            return "error";
        }
        if(!userService.activateUser(user)){
            model.put("messsage", "Couldnt activate user");
            return "error";
        }

        model.put("messsage", "Successfully activated");
        return "redirect:/login";
    }

}
