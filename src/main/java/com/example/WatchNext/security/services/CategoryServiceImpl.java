package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Categories;
import com.example.WatchNext.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Optional<Categories> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Categories> getAll() {
        List<Categories> categories = (List<Categories>) categoryRepository.findAll();

        return categories;
    }
}
