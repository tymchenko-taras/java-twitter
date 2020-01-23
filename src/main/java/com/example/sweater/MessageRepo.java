package com.example.sweater;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    // method name can be combined from key words
    // and described here: https://docs.spring.io/spring-data/jpa/docs/current/reference/html
    List<Message> findByTag(String tag);
    List<Message> findByTagContaining(String tag);
}
