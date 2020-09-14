package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.payload.request.CategoryRequest;
import com.example.WatchNext.payload.response.MessageResponse;
import com.example.WatchNext.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Object findById(Long id) {
       var category = categoryRepository.findById(id);
        if (category.isPresent())
            return category;
        else
            return new ResponseEntity(new MessageResponse("category does not exist"), HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        return categories;
    }

    @Override
    public Category findByName(String name) {
        var category = categoryRepository.findByName(name);
        return category;
    }

    @Override
    public Category save(CategoryRequest categoryRequest) {
        var category = findByName(categoryRequest.getName());
        if (category == null) {
            Category saveCategory = new Category(categoryRequest.getName());
            categoryRepository.save(saveCategory);
            return saveCategory;
        }
        return category;
    }

    }

