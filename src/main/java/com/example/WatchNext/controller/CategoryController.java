package com.example.WatchNext.controller;


import com.example.WatchNext.model.Categories;
import com.example.WatchNext.payload.request.CategoryRequest;
import com.example.WatchNext.payload.response.CategoryResponse;
import com.example.WatchNext.payload.response.MessageResponse;
import com.example.WatchNext.repositories.CategoryRepository;
import com.example.WatchNext.security.services.CategoryService;
import com.example.WatchNext.security.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categoryRequest) {

        if (categoryRepository.existsByName(categoryRequest.getName())) {
            Categories categories = categoryRepository.findByName(categoryRequest.getName());
            return ResponseEntity
                    .badRequest()
                    .body(new CategoryResponse(categories.getId(), categories.getName()));
        }

        Categories categories = new Categories(categoryRequest.getName());
        categoryRepository.save(categories);

        return ResponseEntity.ok(new CategoryResponse(categories.getId(), categories.getName()));
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {

        var val = categoryService.findById(id);
        if (val.isPresent())
            return ResponseEntity.ok(val.get());
        else
            return new ResponseEntity(new MessageResponse("category does not exist"), HttpStatus.NOT_FOUND);

    }

    @GetMapping
    public List<Categories> getAllCategories() {

        List<Categories> categories = categoryService.getAll();
        return categories;
    }

}
