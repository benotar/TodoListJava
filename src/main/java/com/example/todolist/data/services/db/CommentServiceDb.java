package com.example.todolist.data.services.db;

import com.example.todolist.data.repositories.CommentRepository;
import com.example.todolist.data.services.CommentService;
import com.example.todolist.entities.Comment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceDb implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        if (!commentRepository.existsById(comment.getId())) {
            throw new RuntimeException("Comment not found with id: " + comment.getId());
        }

        comment.setUpdatedAt(LocalDate.now());
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public List<Comment> findByTodoId(Integer id) {
        return commentRepository.findByTodo_Id(id);
    }
}
