package com.example.WatchNext.controller;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.security.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    private MockMvc mvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(categoryController)
                .build();
    }

    @Test
    void addCategory() throws Exception {
        String categoryName = "Drama";
        Category testCategory = new Category(categoryName);
        testCategory.setId(1L);
        when(categoryService.save(testCategory)).thenReturn(testCategory);
        mvc.perform(MockMvcRequestBuilders.post("/api/category"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(categoryName)));

    }

    @Test
    void getCategoryById() throws Exception {
        String categoryName = "Drama";
        Category testCategory = new Category(categoryName);
        testCategory.setId(1L);

        when(categoryService.findCategoryById(any(Long.class))).thenReturn(Optional.of(testCategory));

        mvc.perform(MockMvcRequestBuilders.get("/api/category/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(categoryName)));
    }

    @Test
    void getAllCategories() throws Exception {

        String categoryName = "Drama";
        Category testCategory = new Category(categoryName);
        List<Category> allCategory = Collections.nCopies(10, testCategory);

        when(categoryService.getAll()).thenReturn(allCategory);

        mvc.perform(MockMvcRequestBuilders.get("/api/category")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

}