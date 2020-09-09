package com.example.WatchNext.repositories;

import com.example.WatchNext.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movies, Long> {

    Boolean existsByImdbld(String imdbld);

    Movies findByImdbld(String imdbld);


}
