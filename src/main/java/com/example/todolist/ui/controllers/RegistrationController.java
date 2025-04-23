package com.example.todolist.ui.controllers;

import com.example.todolist.data.services.UserEntityService;
import com.example.todolist.entities.UserEntity;
import com.example.todolist.entities.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String load(Model model) {
        return "register";
    }

    @PostMapping("/register-form")
    public String addUserForm(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam("email") String email,
            @RequestParam("name") String name) {

        if (!password.equals(confirmPassword)) {
            return "redirect:/register";
        }

        if (userEntityService.existsByUsername(username) || userEntityService.existsByEmail(email)) {
            return "redirect:/register";
        }


        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .passwordHash(password)
                .email(email)
                .name(name)
                .role(Role.GUEST)
                .build();
        userEntityService.save(userEntity);
        return "redirect:/login";
    }
}