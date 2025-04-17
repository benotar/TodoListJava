package com.example.todolist.data.services;

import com.example.todolist.entities.UserEntity;
import com.example.todolist.entities.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserEntityService {
    UserEntity save(UserEntity user);

    UserEntity update(UserEntity user);

    Optional<UserEntity> findById(Integer id);

    List<UserEntity> findAll();

    void deleteById(Integer id);

    void delete(UserEntity user);

    List<UserEntity> findByUsernameOrEmailIgnoreCase(String username, String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<UserEntity> findByRole(Role role);
}