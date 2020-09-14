package com.example.WatchNext.repositories;

import com.example.WatchNext.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Boolean existsByName(String Name);
    Category findByName(String name);
}
