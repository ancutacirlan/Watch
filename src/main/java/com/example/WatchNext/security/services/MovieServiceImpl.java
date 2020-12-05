package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Movie;
import com.example.WatchNext.repositories.CategoryRepository;
import com.example.WatchNext.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> findMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> findMovieByImdbld(String imdbld) {
        return movieRepository.findByImdbld(imdbld);
    }

    @Override
    public Movie saveMovie(Movie movieRequest) {
                Movie movie = new Movie(movieRequest.getTitle(), movieRequest.getTrailerUrl(),
                movieRequest.getOriginalSourceUrl(), movieRequest.getCoverUrl(), movieRequest.getImdbld(),
                movieRequest.getImdbScore(), movieRequest.getDescription(), movieRequest.getReleaseDate());
                movie.setCategories(movieRequest.getCategories());
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findMovieBetween(Date fromDate, Date toDate) {
        return movieRepository.findByReleaseDateBetween(fromDate, toDate);
    }

}
