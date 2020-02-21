package com.example.sweater.controller;

import com.example.sweater.model.Message;
import com.example.sweater.model.User;
import com.example.sweater.repository.MessageRepository;
import com.example.sweater.service.MessageService;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages/{user}")
    public String getUserMessages(
            @PathVariable User user,
            Map<String, Object> model
    ){
        model.put("username", user.getUsername());
        model.put("messages", user.getMessages());
        return "messages/index";
    }

    @GetMapping("/message/{id}")
    public String getMessage(


            @PathVariable("id") Message id,
            Map<String, Object> model
    ){
        model.put("message", id);
        return "messages/edit";
    }

    @PostMapping("/message/{message}")
    public String postMessage(
            BindingResult bindingResult,
            @PathVariable Message message,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
        if(bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
        } else {
            messageService.save(message, file);
        }
        model.addAttribute("message", message);
        return "messages/edit";
    }
}
