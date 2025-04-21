package com.example.todolist;

import com.example.todolist.data.repositories.UserEntityRepository;
import com.example.todolist.entities.UserEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
    }
}
