package com.example.todolist.data.repositories;

import com.example.todolist.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUser_Id(Integer id);

    List<Todo> findByCompleted(boolean completed);

    List<Todo> findByCategory_Id(Integer id);

    List<Todo> findByTag_Id(Integer id);

    Todo findByTitleIgnoreCase(String title);
}