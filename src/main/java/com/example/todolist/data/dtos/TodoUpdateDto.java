package com.example.todolist.data.dtos;

import lombok.Data;

@Data
public class TodoUpdateDto {
    private Integer id;
    private String title;
    private String description;
    private boolean completed;
    private Integer categoryId;
    private Integer tagId;
}