package com.example.todolist.entities;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "Todoes")
public class Todo extends BaseEntity{
    private String title;
    private String description;
    private boolean completed;

    // Many to One
    // ?????
    private UserEntity user;
    private Category category;
}
