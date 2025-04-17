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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@Rollback(value = true)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TodoRepository todoRepository;

    private Category category;

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

        Tag tag = Tag.builder()
                .name("Urgent")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todos(new ArrayList<>())
                .build();
        tag = tagRepository.save(tag);

        category = Category.builder()
                .name("Work")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todos(new ArrayList<>())
                .build();
        category = categoryRepository.save(category);

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

        category.getTodos().add(todo);
    }

    @Test
    void contextLoads() {
        assertNotNull(categoryRepository, "CategoryRepository should be initialized");
        assertNotNull(category, "Category should be initialized");
    }

    @Test
    void save() {
        Category newCategory = Category.builder()
                .name("Personal")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todos(new ArrayList<>())
                .build();
        Category savedCategory = categoryRepository.save(newCategory);
        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId(), "ID should be generated");
        assertEquals(newCategory.getName(), savedCategory.getName());
    }

    @Test
    void findById() {
        Category savedCategory = categoryRepository.save(category);
        assertNotNull(savedCategory);

        Optional<Category> categoryById = categoryRepository.findById(savedCategory.getId());
        assertTrue(categoryById.isPresent());
        assertEquals(savedCategory.getName(), categoryById.get().getName());
    }

    @Test
    void saveUpdate() {
        Category savedCategory = categoryRepository.save(category);
        assertNotNull(savedCategory);

        String updatedName = "Home";
        savedCategory.setName(updatedName);
        categoryRepository.save(savedCategory);

        Optional<Category> updated = categoryRepository.findById(savedCategory.getId());
        assertTrue(updated.isPresent());
        assertEquals(updatedName, updated.get().getName());
    }

    @Test
    void deleteById() {
        Category savedCategory = categoryRepository.save(category);
        assertNotNull(savedCategory);

        Integer categoryId = savedCategory.getId();
        categoryRepository.deleteById(categoryId);

        Optional<Category> deleted = categoryRepository.findById(categoryId);
        assertFalse(deleted.isPresent());
    }
}