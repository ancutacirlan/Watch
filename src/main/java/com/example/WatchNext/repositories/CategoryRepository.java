package com.example.WatchNext.repositories;

import com.example.WatchNext.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    Boolean existsByName(String Name);

    Categories findByName(String name);
}
