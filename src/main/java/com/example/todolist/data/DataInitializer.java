package com.example.todolist.data;

import com.example.todolist.data.services.CategoryService;
import com.example.todolist.data.services.TagService;
import com.example.todolist.entities.Category;
import com.example.todolist.entities.Tag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final CategoryService categoryService;
//    private final TagService tagService;
//
//    public DataInitializer(CategoryService categoryService, TagService tagService) {
//        this.categoryService = categoryService;
//        this.tagService = tagService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Category category1 = new Category();
//        category1.setName("Work");
//        categoryService.save(category1);
//
//        Category category2 = new Category();
//        category2.setName("Personal");
//        categoryService.save(category2);
//
//        Tag tag1 = new Tag();
//        tag1.setName("Urgent");
//        tagService.save(tag1);
//
//        Tag tag2 = new Tag();
//        tag2.setName("Low Priority");
//        tagService.save(tag2);
//
//        System.out.println("Categories and Tags initialized!");
//    }
//}