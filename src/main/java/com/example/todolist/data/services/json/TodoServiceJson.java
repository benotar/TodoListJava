package com.example.todolist.data.services.json;

import com.example.todolist.data.services.TodoService;
import com.example.todolist.entities.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceJson implements TodoService
{

    private final String fileName = "todos.json";

    @Override
    public Todo save(Todo todo) {
        return null;
    }

    @Override
    public Todo update(Todo todo) {
        return null;
    }

    @Override
    public Optional<Todo> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Todo> findAll() {
        return List.of();
    }

    @Override
    public Todo toggleCompleted(Integer todoId) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(Todo todo) {

    }

    @Override
    public Todo findByTitleIgnoreCase(String title) {
        return null;
    }

    @Override
    public List<Todo> findByTagId(Integer tagId) {
        return List.of();
    }

    @Override
    public List<Todo> findByCategoryId(Integer categoryId) {
        return List.of();
    }

    @Override
    public List<Todo> findByCompleted(boolean completed) {
        return List.of();
    }

    @Override
    public List<Todo> findByUserId(Integer userId) {
        return List.of();
    }
}
