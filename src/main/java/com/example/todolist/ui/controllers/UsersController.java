package com.example.todolist.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UsersController {

    @GetMapping("/users") // route
    public String load() {
        return "users"; // html name
    }

//    @PostMapping("/users-form")
//    public String usersForm(@ModelAttribute Ma p map)
//    {
//        return "redirect:/users";
//    }
}
