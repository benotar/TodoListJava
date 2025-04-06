package com.example.todolist.data.services;

import com.example.todolist.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);

    Comment update(Comment comment);

    Optional<Comment> findById(Integer id);

    List<Comment> findAll();

    void deleteById(Integer id);

    void delete(Comment comment);

    List<Comment> findByTodoId(Integer id);
}