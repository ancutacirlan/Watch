package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Movie;
import com.example.WatchNext.payload.request.MovieRequest;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    void deleteMovieById(Long id);
    Movie findMovieById(Long id);
    Optional<Movie> findMovieByImdbld(String imdbld);
    Movie saveMovie(MovieRequest movieRequest);
    List<Movie> findAllMovies();

}
