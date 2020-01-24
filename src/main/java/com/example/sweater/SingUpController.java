package com.example.sweater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class SingUpController {

    @Autowired
    private UserRepo userRepo;

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

        User user = userRepo.findByUsername(username);
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
        userRepo.save(user);

        return "redirect:/login";
    }

    @GetMapping("/loginerror")
    public String login(HttpServletRequest request, Model model) {

        // debug to figure out what happened
        Object a = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        //((Exception)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage();
        return "";
    }
}
