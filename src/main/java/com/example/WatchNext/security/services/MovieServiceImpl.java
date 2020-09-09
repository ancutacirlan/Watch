package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Movies;
import com.example.WatchNext.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Optional<Movies> findMoviesById(Long id) {
        return movieRepository.findById(id);
    }


    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }
}
