package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void findCategoryById() throws Exception{
        Category category = new Category("Drama");
        category.setId(1L);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        assertEquals(category.getId(),categoryService.findCategoryById(category.getId()).get().getId());
    }

    @Test
    void getAll() throws Exception {
        Category category = mock(Category.class);
        List<Category> categories = Collections.nCopies(3,category);
        when(categoryRepository.findAll()).thenReturn(categories);
        List<Category> command =categoryService.getAll();
        assertEquals(3,command.size());
    }

    @Test
    void save() throws Exception {
        Category category =  mock(Category.class);
        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(category.getName(),categoryService.save(category).getName());
    }

    @Test
    void findCategoryByName() throws Exception {
        Category category = mock(Category.class);
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));
        assertEquals(category.getName(),
                categoryService.findCategoryByName(category.getName()).get().getName());
    }
}