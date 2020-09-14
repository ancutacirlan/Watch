package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.payload.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    Object findById(Long id);
    List<Category> getAll();
    Category findByName(String name);
    Category save (CategoryRequest categoryRequest);
}
