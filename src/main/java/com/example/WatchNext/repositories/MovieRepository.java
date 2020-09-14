package com.example.WatchNext.repositories;

import com.example.WatchNext.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByImdbld(String imdbld);

}
