package com.example.sweater.controller;
import com.example.sweater.model.Message;
import com.example.sweater.repository.MessageRepository;
import com.example.sweater.model.User;
import com.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class GreetingsController {
    @Autowired
    private MessageRepository repository;

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name); //param for template
        return "greetings"; // template name
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Map<String, Object> model
    ) {

        Iterable messages;
        if(filter != ""){
            messages = repository.findByTagContaining(filter);
        } else {
            messages = repository.findAll();
        }
        model.put("filter", filter);
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
        message.setAuthor(user);
        if(bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
        } else {
            messageService.save(message, file);
        }

        Iterable messages = repository.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("filter", "");

        return "main";
    }
}
