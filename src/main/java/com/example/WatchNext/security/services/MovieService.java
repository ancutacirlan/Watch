package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Movies;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<Movies> findMoviesById(Long id);

    List<Movies>  getAllMovies();

    void deleteMovieById(Long id);
}
