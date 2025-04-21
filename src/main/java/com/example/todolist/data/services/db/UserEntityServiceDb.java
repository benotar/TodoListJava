package com.example.todolist.data.services.db;

import com.example.todolist.data.repositories.UserEntityRepository;
import com.example.todolist.data.services.UserEntityService;
import com.example.todolist.entities.UserEntity;
import com.example.todolist.entities.enums.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserEntityServiceDb implements UserEntityService {

    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserEntity save(UserEntity user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        System.out.println("save db: " + user);
        UserEntity savedUserEntity =  userRepository.save(user);
        System.out.println("save db: " + savedUserEntity);
        return savedUserEntity;
    }

    @Override
    public UserEntity update(UserEntity user) {
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("User not found with id: " + user.getId());
        }

        user.setUpdatedAt(LocalDate.now());
        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(UserEntity user) {
        userRepository.delete(user);
    }

    @Override
    public List<UserEntity> findByUsernameOrEmailIgnoreCase(String username, String email) {
        return userRepository.findByUsernameIgnoreCaseOrEmailIgnoreCase(username, email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public List<UserEntity> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public Optional<UserEntity> findByUsernameIgnoreCase(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }
}