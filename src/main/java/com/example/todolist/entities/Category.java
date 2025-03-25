package com.example.todolist.entities;

import jakarta.persistence.Table;

import java.util.List;

@Table(name = "Categories")
public class Category extends BaseEntity{
    private String name;

    // One to many
    // ?????
    private List<Todo> todoes;
}
