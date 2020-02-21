package com.example.sweater.repository;

import com.example.sweater.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    // method name can be combined from key words
    // and described here: https://docs.spring.io/spring-data/jpa/docs/current/reference/html
    List<Message> findByTag(String tag);
    List<Message> findByTagContaining(String tag);
    Optional<Message> findById(Long var1);
}
