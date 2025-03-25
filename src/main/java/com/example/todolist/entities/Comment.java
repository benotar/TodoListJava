package com.example.todolist.entities;

import jakarta.persistence.Table;

import java.util.List;

@Table(name = "Comments")
public class Comment extends BaseEntity{
    private String text;

    // One to many
    // ?????
    private List<Todo> todoes;
}
