package com.example.todolist.data.repositories;

import com.example.todolist.entities.Category;
import com.example.todolist.entities.Tag;
import com.example.todolist.entities.Todo;
import com.example.todolist.entities.UserEntity;
import com.example.todolist.entities.enums.Role;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Rollback(value = true)
class TodoEntityRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    private List<Todo> todoList;

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

        todoList = new ArrayList<>();
        Todo todo1 = Todo.builder()
                .title("Test Todo 1")
                .description("This is a test task 1")
                .completed(false)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .user(user)
                .category(category)
                .tag(tag)
                .comments(new ArrayList<>())
                .build();

        Todo todo2 = Todo.builder()
                .title("Test Todo 2")
                .description("This is a test task 2")
                .completed(false)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .user(user)
                .category(category)
                .tag(tag)
                .comments(new ArrayList<>())
                .build();

        todoList.add(todo1);
        todoList.add(todo2);

        user.getTodos().addAll(todoList);
        category.getTodos().addAll(todoList);
        tag.getTodos().addAll(todoList);
    }

    @Test
    void contextLoads() {
        assertNotNull(todoRepository, "TodoRepository should be initialized");
        assertNotNull(userRepository, "UserRepository should be initialized");
        assertNotNull(categoryRepository, "CategoryRepository should be initialized");
        assertNotNull(tagRepository, "TagRepository should be initialized");
        assertFalse(todoList.isEmpty(), "TodoList should not be empty");
    }

    @Test
    void save() {
        Todo savedTodo = todoRepository.save(todoList.getFirst());
        assertNotNull(savedTodo);
        assertNotNull(savedTodo.getId(), "ID should be generated");
        assertEquals(todoList.getFirst().getTitle(), savedTodo.getTitle());
    }

    @Test
    void findById() {
        Todo savedTodo = todoRepository.save(todoList.getFirst());
        assertNotNull(savedTodo);

        Optional<Todo> todoById = todoRepository.findById(savedTodo.getId());
        assertTrue(todoById.isPresent());
        assertEquals(savedTodo.getTitle(), todoById.get().getTitle());
    }

    @Test
    void saveUpdate() {
        Todo savedTodo = todoRepository.save(todoList.getFirst());
        assertNotNull(savedTodo);

        String updatedTitle = "Updated Title";
        savedTodo.setTitle(updatedTitle);
        todoRepository.save(savedTodo);

        Optional<Todo> updated = todoRepository.findById(savedTodo.getId());
        assertTrue(updated.isPresent());
        assertEquals(updatedTitle, updated.get().getTitle());
    }

    @Test
    void deleteById() {
        Todo savedTodo = todoRepository.save(todoList.getFirst());
        assertNotNull(savedTodo);

        Integer todoId = savedTodo.getId();
        todoRepository.deleteById(todoId);

        Optional<Todo> deleted = todoRepository.findById(todoId);
        assertFalse(deleted.isPresent());
    }
}