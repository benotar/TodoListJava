package com.example.todolist.data.services;

import com.example.todolist.data.services.qualifiers.TodoServiceDbQualifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoServiceTest {

    @TodoServiceDbQualifier
    @Autowired
    private TodoService todoService;

    @Test
    void save() {
        System.out.println(todoService);
    }

}
