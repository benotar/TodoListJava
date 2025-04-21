package com.example.todolist.beans;

import com.example.todolist.entities.*;
import com.example.todolist.entities.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@TestConfiguration
public class EntitiesGenerator {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Scope("prototype")
    @Bean
    public List<UserEntity> userEntityList() {
        return List.of(
                UserEntity.builder()
                        .username("a")
                        .passwordHash(passwordEncoder.encode("a"))
                        .email("user1@example.com")
                        .role(Role.USER)
                        .name("User One")
                        .createdAt(LocalDate.now())
                        .updatedAt(LocalDate.now())
                        .todos(new ArrayList<>())
                        .build(),

                UserEntity.builder()
                        .username("user2")
                        .passwordHash(passwordEncoder.encode("pass2"))
                        .email("user2@example.com")
                        .role(Role.ADMIN)
                        .name("User Two")
                        .createdAt(LocalDate.now())
                        .updatedAt(LocalDate.now())
                        .todos(new ArrayList<>())
                        .build()
        );
    }

    @Scope("prototype")
    @Bean
    public UserEntity userEntity() {
        return UserEntity.builder()
                .username("admin")
                .passwordHash(passwordEncoder.encode("admin"))
                .email("admin@example.com")
                .role(Role.ADMIN)
                .name("Admin User")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todos(new ArrayList<>())
                .build();
    }
}