package com.example.todolist.ui.controllers;

import com.example.todolist.data.services.UserEntityService;
import com.example.todolist.entities.UserEntity;
import com.example.todolist.entities.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {

    private final UserEntityService userEntityService;

    @Autowired
    public UsersController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/users")
    public String load(Model model) {
        List<UserEntity> users = userEntityService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/add-users-form")
    public String addUserForm(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("role") String role) {
        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .passwordHash(password) // Буде зашифровано в сервісі
                .email(email)
                .name(name)
                .role(Role.valueOf(role))
                .build();
        userEntityService.save(userEntity);
        return "redirect:/users";
    }
}