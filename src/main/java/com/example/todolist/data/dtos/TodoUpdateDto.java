package com.example.todolist.data.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class TodoUpdateDto {
    private Integer id;
    private String title;
    private String description;
    private boolean completed;
    private Integer categoryId;
    private Integer tagId;

}