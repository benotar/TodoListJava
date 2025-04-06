package com.example.todolist.data.services;

import com.example.todolist.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(Category category);

    Category update(Category category);

    Optional<Category> findById(Integer id);

    List<Category> findAll();

    void deleteById(Integer id);

    void delete(Category category);

    Category findByName(String name);
}