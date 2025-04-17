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
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserEntityRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TodoRepository todoRepository;

    private Tag tag;

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

        tag = Tag.builder()
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

        tag.getTodos().add(todo);
    }

    @Test
    void contextLoads() {
        assertNotNull(tagRepository, "TagRepository should be initialized");
        assertNotNull(tag, "Tag should be initialized");
    }

    @Test
    void save() {
        Tag newTag = Tag.builder()
                .name("Important")
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .todos(new ArrayList<>())
                .build();
        Tag savedTag = tagRepository.save(newTag);
        assertNotNull(savedTag);
        assertNotNull(savedTag.getId(), "ID should be generated");
        assertEquals(newTag.getName(), savedTag.getName());
    }

    @Test
    void findById() {
        Tag savedTag = tagRepository.save(tag);
        assertNotNull(savedTag);

        Optional<Tag> tagById = tagRepository.findById(savedTag.getId());
        assertTrue(tagById.isPresent());
        assertEquals(savedTag.getName(), tagById.get().getName());
    }

    @Test
    void saveUpdate() {
        Tag savedTag = tagRepository.save(tag);
        assertNotNull(savedTag);

        String updatedName = "Critical";
        savedTag.setName(updatedName);
        tagRepository.save(savedTag);

        Optional<Tag> updated = tagRepository.findById(savedTag.getId());
        assertTrue(updated.isPresent());
        assertEquals(updatedName, updated.get().getName());
    }

    @Test
    void deleteById() {
        Tag savedTag = tagRepository.save(tag);
        assertNotNull(savedTag);

        Integer tagId = savedTag.getId();
        tagRepository.deleteById(tagId);

        Optional<Tag> deleted = tagRepository.findById(tagId);
        assertFalse(deleted.isPresent());
    }
}