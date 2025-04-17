package com.example.todolist.data.repositories;

import com.example.todolist.entities.*;
import com.example.todolist.entities.enums.Role;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Rollback(value = true)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    private Comment comment;

    @BeforeEach
    void setUp() {
        UserEntity user = UserEntity.builder()
                .username("admin")
                .passwordHash("encoded_password")
                .email("admin@example.com")
                .role(Role.ADMIN)
                .name("Admin User")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todos(new ArrayList<>())
                .build();
        user = userRepository.save(user);

        Category category = Category.builder()
                .name("Work")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todos(new ArrayList<>())
                .build();
        category = categoryRepository.save(category);

        Tag tag = Tag.builder()
                .name("Urgent")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todos(new ArrayList<>())
                .build();
        tag = tagRepository.save(tag);

        Todo todo = Todo.builder()
                .title("Test Todo")
                .description("This is a test task")
                .completed(false)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .user(user)
                .category(category)
                .tag(tag)
                .comments(new ArrayList<>())
                .build();
        todo = todoRepository.save(todo);

        comment = Comment.builder()
                .text("This is a test comment")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todo(todo)
                .build();

        todo.getComments().add(comment);
    }

    @Test
    void contextLoads() {
        assertNotNull(commentRepository, "CommentRepository should be initialized");
        assertNotNull(comment, "Comment should be initialized");
    }

    @Test
    void save() {
        Comment newComment = Comment.builder()
                .text("New test comment")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todo(comment.getTodo())
                .build();
        Comment savedComment = commentRepository.save(newComment);
        assertNotNull(savedComment);
        assertNotNull(savedComment.getId(), "ID should be generated");
        assertEquals(newComment.getText(), savedComment.getText());
    }

    @Test
    void findById() {
        Comment savedComment = commentRepository.save(comment);
        assertNotNull(savedComment);

        Optional<Comment> commentById = commentRepository.findById(savedComment.getId());
        assertTrue(commentById.isPresent());
        assertEquals(savedComment.getText(), commentById.get().getText());
    }

    @Test
    void saveUpdate() {
        Comment savedComment = commentRepository.save(comment);
        assertNotNull(savedComment);

        String updatedText = "Updated comment text";
        savedComment.setText(updatedText);
        commentRepository.save(savedComment);

        Optional<Comment> updated = commentRepository.findById(savedComment.getId());
        assertTrue(updated.isPresent());
        assertEquals(updatedText, updated.get().getText());
    }

    @Test
    void deleteById() {
        Comment savedComment = commentRepository.save(comment);
        assertNotNull(savedComment);

        Integer commentId = savedComment.getId();
        commentRepository.deleteById(commentId);

        Optional<Comment> deleted = commentRepository.findById(commentId);
        assertFalse(deleted.isPresent());
    }
}