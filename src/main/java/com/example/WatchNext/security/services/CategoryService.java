package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoryService {


    Optional<Categories> findById(Long id);

    List<Categories> getAll();

}
