package com.example.todolist.data.repositories;

import com.example.todolist.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByTodo_Id(Integer id);
}