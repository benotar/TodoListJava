package com.example.todolist.ui.controllers;

import com.example.todolist.data.services.CategoryService;
import com.example.todolist.data.services.TagService;
import com.example.todolist.data.services.TodoService;
import com.example.todolist.data.services.qualifiers.TodoServiceDbQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoUpdateController {
    @TodoServiceDbQualifier
    @Autowired
    private TodoService todoService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @GetMapping("/todo-update")
    public String load(Model model) {
        try {
            Integer todoId = (Integer) model.getAttribute("todoId");

            if (todoId == null) {
                return "redirect:/todos";
            }

            todoService.findById(todoId).ifPresent(todo -> {
                model.addAttribute("todo", todo);
                model.addAttribute("categories", categoryService.findAll());
                model.addAttribute("tags", tagService.findAll());
            });

            return "todo-update";
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return "error";
        }
    }
}
