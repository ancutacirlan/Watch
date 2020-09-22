package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findCategoryById(Long id);

    List<Category> getAll();

    Category save(Category category);

    Optional<Category> findCategoryByName(String name);

}
