package com.example.todolist.ui.controllers.restcontrollers;

import com.example.todolist.data.dtos.UserUpdateDto;
import com.example.todolist.data.services.UserEntityService;
import com.example.todolist.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class UserUpdateRestController {
    @Autowired
    private UserEntityService userEntityService;

    @PostMapping("/rest/user-update-form")
    public ResponseEntity<?> userUpdate(@RequestBody UserUpdateDto userDto) {
        try {
            System.err.println(userDto);

            userEntityService.findById(userDto.getId()).ifPresent(userDb -> {
                userDb.setName(userDto.getName());
                userDb.setEmail(userDto.getEmail());
                userDb.setUsername(userDto.getUsername());
                userDb.setRole(userDto.getRole());
                userDb.setUpdatedAt(LocalDate.now());

                userEntityService.update(userDb);
            });

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
