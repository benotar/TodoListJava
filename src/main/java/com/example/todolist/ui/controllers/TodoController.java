package com.example.todolist.ui.controllers;

import com.example.todolist.data.services.CategoryService;
import com.example.todolist.data.services.TagService;
import com.example.todolist.data.services.TodoService;
import com.example.todolist.data.services.UserEntityService;
import com.example.todolist.data.services.qualifiers.TodoServiceDbQualifier;
import com.example.todolist.entities.Category;
import com.example.todolist.entities.Tag;
import com.example.todolist.entities.Todo;
import com.example.todolist.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService todoService;
    private final UserEntityService userEntityService;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Autowired
    public TodoController(@TodoServiceDbQualifier TodoService todoService, UserEntityService userEntityService, CategoryService categoryService, TagService tagService) {
        this.todoService = todoService;
        this.userEntityService = userEntityService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @GetMapping("/todos")
    public String load(Model model) {
        List<Todo> todos = todoService.findAll();
        List<UserEntity> users = userEntityService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Tag> tags = tagService.findAll();

        model.addAttribute("todos", todos);
        model.addAttribute("users", users);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);

        return "todos";
    }

    @PostMapping("/add-todos-form")
    public String addTodoForm(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(value = "completed", defaultValue = "false") boolean completed,
            @RequestParam("userId") Integer userId,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("tagId") Integer tagId) {
        UserEntity user = userEntityService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        Category category = categoryService.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));
        Tag tag = tagService.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("Tag not found with ID: " + tagId));

        Todo todo = Todo.builder()
                .title(title)
                .description(description)
                .completed(completed)
                .user(user)
                .category(category)
                .tag(tag)
                .build();
        todoService.save(todo);
        return "redirect:/todos";
    }

    @PostMapping("/todo-update-redirect-form")
    public String todoUpdateRedirect(@RequestParam("todoId") Integer todoId,
                                     RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("todoId", todoId);
        return "redirect:/todo-update";
    }
}
