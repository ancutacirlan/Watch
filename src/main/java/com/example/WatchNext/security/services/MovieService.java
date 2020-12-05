package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Movie;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    void deleteMovieById(Long id);

    Optional<Movie> findMovieById(Long id);

    Optional<Movie> findMovieByImdbld(String imdbld);

    Movie saveMovie(Movie movie);

    List<Movie> findAllMovies();

    Optional<Movie> findMovieBetween(Date fromDate, Date toDate);

}
