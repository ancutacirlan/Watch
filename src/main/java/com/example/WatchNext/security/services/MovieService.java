package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Movies;

import java.util.Optional;

public interface MovieService {

    Optional<Movies> findMoviesById(Long id);
    void deleteMovieById(Long id);
}
