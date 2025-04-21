package com.example.todolist.security;

import com.example.todolist.beans.EntitiesGenerator;
import com.example.todolist.data.repositories.UserEntityRepository;
import com.example.todolist.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {EntitiesGenerator.class})
public class CreateUserTest {

    //@Qualifier("userEntityService")
    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    List<UserEntity> entities;
    @Test
    void save()
    {
        userEntityRepository.save(entities.getFirst());
    }
}
