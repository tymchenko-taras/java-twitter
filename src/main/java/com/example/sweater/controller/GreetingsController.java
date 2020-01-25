package com.example.sweater.controller;
import com.example.sweater.model.Message;
import com.example.sweater.repository.MessageRepository;
import com.example.sweater.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class GreetingsController {
@Autowired
private MessageRepository repository;

@Value("${upload.path}")
private String uploadPath;

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
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model
    ) throws IOException {
        Message message = new Message(text, tag, user);
        if(file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            String filename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            file.transferTo(new File(uploadPath+"/"+filename));
            message.setFilename(filename);
        }
        repository.save(message);

        Iterable messages = repository.findAll();
        model.put("messages", messages);
        model.put("filter", "");

        return "main";
    }
}
