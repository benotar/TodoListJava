package com.example.todolist.data.services;

import com.example.todolist.entities.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    Todo save(Todo todo);

    Todo update(Todo todo);

    Optional<Todo> findById(Integer id);

    List<Todo> findAll();

    Todo toggleCompleted(Integer todoId);

    void deleteById(Integer id);

    void delete(Todo todo);

    Todo findByTitleIgnoreCase(String title);

    List<Todo> findByTagId(Integer tagId);

    List<Todo> findByCategoryId(Integer categoryId);

    List<Todo> findByCompleted(boolean completed);

    List<Todo> findByUserId(Integer userId);
}