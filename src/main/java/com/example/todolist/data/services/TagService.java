package com.example.todolist.data.services;

import com.example.todolist.entities.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Tag save(Tag tag);

    Tag update(Tag tag);

    Optional<Tag> findById(Integer id);

    List<Tag> findAll();

    void deleteById(Integer id);

    void delete(Tag tag);

    Tag findByName(String name);
}
