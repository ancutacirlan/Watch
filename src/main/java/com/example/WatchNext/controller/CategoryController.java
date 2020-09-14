package com.example.WatchNext.controller;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.payload.request.CategoryRequest;
import com.example.WatchNext.security.services.CategoryService;
import com.example.WatchNext.security.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryRequest categoryRequest) {
        var saveCategory = categoryService.save(categoryRequest);
        return ResponseEntity.ok(saveCategory);
    }


    @GetMapping("/{id}")
    public Object getCategoryById(@PathVariable Long id) {
        var val = categoryService.findById(id);
        return val;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        List<Category> categories = categoryService.getAll();
        return categories;
    }

}
