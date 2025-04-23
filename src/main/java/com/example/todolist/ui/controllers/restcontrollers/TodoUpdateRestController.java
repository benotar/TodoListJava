package com.example.todolist.ui.controllers.restcontrollers;

import com.example.todolist.data.dtos.TodoUpdateDto;
import com.example.todolist.data.repositories.TodoRepository;
import com.example.todolist.data.services.CategoryService;
import com.example.todolist.data.services.TagService;
import com.example.todolist.data.services.TodoService;
import com.example.todolist.data.services.qualifiers.TodoServiceDbQualifier;
import com.example.todolist.entities.Category;
import com.example.todolist.entities.Tag;
import com.example.todolist.entities.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class TodoUpdateRestController {

    @TodoServiceDbQualifier
    @Autowired
    private TodoService todoService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @PostMapping("/rest/todo-update-form")
    public ResponseEntity<?> todoUpdate(@RequestBody TodoUpdateDto dto) {
        try {
            return todoService.findById(dto.getId())
                    .map(todoDb -> {
                        todoDb.setTitle(dto.getTitle());
                        todoDb.setDescription(dto.getDescription());
                        todoDb.setCompleted(dto.isCompleted());

                        Category category = categoryService.findById(dto.getCategoryId())
                                .orElseThrow(() -> new RuntimeException("Category not found"));
                        todoDb.setCategory(category);

                        Tag tag = tagService.findById(dto.getTagId())
                                .orElseThrow(() -> new RuntimeException("Tag not found"));
                        todoDb.setTag(tag);

                        todoService.update(todoDb);
                        return new ResponseEntity<>(HttpStatus.OK);
                    })
                    .orElseGet(() -> new ResponseEntity<>("Todo not found", HttpStatus.NOT_FOUND));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
