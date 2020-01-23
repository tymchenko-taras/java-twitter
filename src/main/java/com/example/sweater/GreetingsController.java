package com.example.sweater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingsController {
@Autowired
private MessageRepo repository;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name); //param for template
        return "greetings"; // template name
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable messages = repository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model
    ) {
        Message message = new Message(text, tag);
        repository.save(message);

        Iterable messages = repository.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("/filter")
    public String filter(
            @RequestParam String filter,
            Map<String, Object> model
    ){
        Iterable messages;

        if(filter != ""){
            messages = repository.findByTagContaining(filter);
        } else {
            messages = repository.findAll();
        }
        model.put("messages", messages);
        return "main";
    }


}
