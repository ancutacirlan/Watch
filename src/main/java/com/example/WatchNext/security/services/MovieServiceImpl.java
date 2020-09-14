package com.example.WatchNext.security.services;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.model.Movie;
import com.example.WatchNext.payload.request.MovieRequest;
import com.example.WatchNext.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

    }


    @Override
    public void deleteMovieById(Long id) {
        var movie = findMovieById(id);
        movieRepository.deleteById(id);
    }

    @Override
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<Movie> findMovieByImdbld(String imdbld) {
        return movieRepository.findByImdbld(imdbld);
    }

    @Override
    public Movie saveMovie(MovieRequest movieRequest) {
        Movie newMovie = new Movie(movieRequest.getTitle(), movieRequest.getTrailerURL(),
                movieRequest.getOriginalSourceUrl(), movieRequest.getCoverUrl(), movieRequest.getImdbld(),
                movieRequest.getImdbScore(), movieRequest.getDescription(), movieRequest.getReleaseDate());
        List<Category> categories = movieRequest.getCategories();
        newMovie.setCategories(categories);
        movieRepository.save(newMovie);
        return newMovie;
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

}
