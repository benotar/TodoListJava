package com.example.todolist.data.services.db;

import com.example.todolist.data.repositories.TagRepository;
import com.example.todolist.data.services.TagService;
import com.example.todolist.entities.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TagServiceDb implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Tag tag) {
        if (!tagRepository.existsById(tag.getId())) {
            throw new RuntimeException("Tag not found with id: " + tag.getId());
        }

        tag.setUpdatedAt(LocalDate.now());
        return tagRepository.save(tag);
    }

    @Override
    public Optional<Tag> findById(Integer id) {
        return tagRepository.findById(id);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        tagRepository.deleteById(id);
    }

    @Override
    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }
}
