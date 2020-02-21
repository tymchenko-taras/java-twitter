package com.example.sweater.model.converter;

import com.example.sweater.model.Message;
import com.example.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringToMessageConverter implements Converter<String, Message> {

    @Autowired
    private MessageRepository messageRepository;

    public Message convert(String source)  {
        Optional<Message> message =  messageRepository.findById(Long.parseLong(source));
        try {
            return message.orElseThrow(() -> new Exception("Not found"));
        } catch (Exception e) {
            e.printStackTrace();
            return new Message();
            // how to handle this?
        }
    }
}