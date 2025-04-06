package com.example.todolist.data.services.db;

import com.example.todolist.data.repositories.TodoRepository;
import com.example.todolist.data.services.TodoService;
import com.example.todolist.entities.Todo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TodoServiceDb implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> findByUserId(Integer userId) {
        return todoRepository.findByUser_Id(userId);
    }

    @Override
    public List<Todo> findByCompleted(boolean completed) {
        return todoRepository.findByCompleted(completed);
    }

    @Override
    public List<Todo> findByCategoryId(Integer categoryId) {
        return todoRepository.findByCategory_Id(categoryId);
    }

    @Override
    public List<Todo> findByTagId(Integer tagId) {
        return todoRepository.findByTag_Id(tagId);
    }

    @Override
    public Todo findByTitleIgnoreCase(String title) {
        return todoRepository.findByTitleIgnoreCase(title);
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo update(Todo todo) {
        if (!todoRepository.existsById(todo.getId())) {
            throw new RuntimeException("Todo not found with id: " + todo.getId());
        }

        todo.setUpdatedAt(LocalDate.now());
        return todoRepository.save(todo);
    }

    @Override
    public Optional<Todo> findById(Integer id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo toggleCompleted(Integer todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + todoId));

        todo.setCompleted(!todo.isCompleted());
        todo.setUpdatedAt(LocalDate.now());

        return todoRepository.save(todo);
    }

    @Override
    public void deleteById(Integer id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void delete(Todo todo) {
        todoRepository.delete(todo);
    }
}