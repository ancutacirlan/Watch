package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.payload.request.CategoryRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findCategoryById(Long id);

    List<Category> getAll();

    Category save(CategoryRequest categoryRequest);

    Optional<Category> findCategoryByName(String name);

}
