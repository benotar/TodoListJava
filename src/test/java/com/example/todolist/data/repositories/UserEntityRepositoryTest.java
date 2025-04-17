package com.example.todolist.data.repositories;

import com.example.todolist.beans.EntitiesGenerator;
import com.example.todolist.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@Rollback(value = true)
@SpringBootTest(classes = {EntitiesGenerator.class})
class UserEntityRepositoryTest {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired()
    List<UserEntity> userEntityList;


    @Test
    void save() {
        UserEntity savedUser = userEntityRepository.save(userEntityList.getFirst());
        assertNotNull(savedUser);
        assertEquals(userEntityList.getFirst().getId(), savedUser.getId());
    }

    @Test
    void findById() {

        UserEntity savedUser = userEntityRepository.save(userEntityList.getFirst());

        assertNotNull(savedUser);

        Optional<UserEntity> userById = userEntityRepository.findById(savedUser.getId());

        assertTrue(userById.isPresent());
        assertEquals(savedUser.getUsername(), userById.get().getUsername());
    }

    @Test
    void saveUpdate() {
        UserEntity savedUser = userEntityRepository.save(userEntityList.getFirst());

        assertNotNull(savedUser);

        String updatedEmail = "updated@gmail.com";

        savedUser.setEmail(updatedEmail);
        userEntityRepository.save(savedUser);

        Optional<UserEntity> updated = userEntityRepository.findById(savedUser.getId());
        assertTrue(updated.isPresent());
        assertEquals(updatedEmail, updated.get().getEmail());
    }

    @Test
    void deleteById() {
        UserEntity savedUser = userEntityRepository.save(userEntityList.getFirst());

        assertNotNull(savedUser);

        Integer userId = savedUser.getId();

        userEntityRepository.deleteById(userId);

        Optional<UserEntity> deleted = userEntityRepository.findById(userId);
        assertFalse(deleted.isPresent());
    }
}