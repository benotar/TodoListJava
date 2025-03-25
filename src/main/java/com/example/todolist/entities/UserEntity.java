package com.example.todolist.entities;

import jakarta.persistence.Table;

import java.util.List;

@Table(name = "Users")
public class UserEntity extends BaseEntity {
    private String userName;

    // ? Hash for password
    //private string passwordHash;

    private String name;

    // One to many
    // ?????
    private List<Todo> todoes;
}