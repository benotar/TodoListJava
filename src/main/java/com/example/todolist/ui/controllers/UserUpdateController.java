package com.example.todolist.ui.controllers;

import com.example.todolist.data.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserUpdateController {
    @Autowired
    public UserEntityService userService;

    @GetMapping("/user-update")
    public String load(Model model) {
        try {
            Integer userId = (Integer) model.getAttribute("userId");

            if (userId == null) {
                return "redirect:/users";
            }

            userService.findById(userId).ifPresent(user -> {
                model.addAttribute("user", user);
            });

            return "user-update";
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return "error";
        }
    }
}
