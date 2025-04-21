package com.example.todolist.security;

import com.example.todolist.data.services.UserEntityService;
import com.example.todolist.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserEntityService userEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userEntityService.findByUsernameIgnoreCase(username);
        if (optionalUserEntity.isPresent())
            return new UserDetailsImpl(optionalUserEntity.get());
        System.err.println("User not found!");
        throw new UsernameNotFoundException("User not found!");
    }
}
