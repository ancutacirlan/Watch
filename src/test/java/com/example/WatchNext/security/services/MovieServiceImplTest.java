package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.model.Movie;
import com.example.WatchNext.payload.request.MovieRequest;
import com.example.WatchNext.repositories.CategoryRepository;
import com.example.WatchNext.repositories.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceImplTest {

    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository, categoryRepository);
    }

    @Test
    void deleteMovieById() {
        Movie movie = mock(Movie.class);
        movieService.deleteMovieById(movie.getId());
        assertEquals(0,movieRepository.findAll().size());
    }

    @Test
    void findMovieById() {
        Movie movie = mock(Movie.class);
        movieRepository.deleteById(movie.getId());
        assertEquals(movie.getId(), movieService.findMovieById(movie.getId()).get().getId());
    }

    @Test
    void findMovieByImdbld() {
        Movie movie = mock(Movie.class);
        when(movieRepository.findByImdbld(movie.getImdbld())).thenReturn(Optional.of(movie));
        assertEquals(movie.getImdbld(),
                movieService.findMovieByImdbld(movie.getImdbld()).get().getId());
    }

    @Test
    void saveMovie() {
        Movie movie = mock(Movie.class);
        movieRepository.save(movie);
        when(movieRepository.save(movie)).thenReturn(movie);
        assertEquals(movie.getTitle(), movieService.saveMovie(movie).getTitle());
    }

    @Test
    void findAllMovies() {
        Movie movie = mock(Movie.class);
        List<Movie> movies = Collections.nCopies(3, movie);
        when(movieRepository.findAll()).thenReturn(movies);
        List<Movie> command = movieService.findAllMovies();
        assertEquals(3, command.size());
    }
}