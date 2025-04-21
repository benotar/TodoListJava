package com.example.todolist.data.repositories;

import com.example.todolist.entities.UserEntity;
import com.example.todolist.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);

    boolean existsByUsernameIgnoreCase(String username);

    boolean existsByEmailIgnoreCase(String email);

    List<UserEntity> findByRole(Role role);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);
}
