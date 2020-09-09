package com.example.WatchNext.controller;

import com.example.WatchNext.model.Categories;
import com.example.WatchNext.model.Movies;
import com.example.WatchNext.payload.request.MovieRequest;
import com.example.WatchNext.payload.response.MessageResponse;
import com.example.WatchNext.payload.response.MovieResponse;
import com.example.WatchNext.repositories.CategoryRepository;
import com.example.WatchNext.repositories.MovieRepository;
import com.example.WatchNext.security.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/movie")
public class MovieController {


    MovieService movieService;
    MovieRepository movieRepository;
    CategoryRepository categoryRepository;

    public MovieController(MovieService movieService, MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }



    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {

        var val = movieService.findMoviesById(id);
        if (val.isPresent())
            return ResponseEntity.ok(val.get());
        else
            return new ResponseEntity(new MessageResponse("Movies does not exist"), HttpStatus.NOT_FOUND);

    }

    @DeleteMapping
    @RequestMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long id) {
        var val = movieService.findMoviesById(id);
        if (val.isPresent()) {
            movieService.deleteMovieById(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

    }


    @PostMapping
    @RequestMapping("/post")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addMovies(@RequestBody MovieRequest movieRequest) {

        if (movieRepository.existsByImdbld(movieRequest.getImdbld())) {
            Movies movies = movieRepository.findByImdbld(movieRequest.getImdbld());
            return ResponseEntity
                    .badRequest()
                    .body(movies.getOriginalSourceUrl());
        }

        Movies movies = new Movies(movieRequest.getTitle(), movieRequest.getTrailerURL(),
                movieRequest.getOriginalSourceUrl(), movieRequest.getCoverUrl(), movieRequest.getImdbld(),
                movieRequest.getImdbScore(), movieRequest.getDescription(), movieRequest.getReleaseDate());

        List<String> strCategories = movieRequest.getCategories();
        List<Categories> categories = new ArrayList<>();

        if (strCategories.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Categories not found"));
        } else
            strCategories.forEach(category -> {
                if (categoryRepository.existsByName(category)) {
                    Categories categories1 = categoryRepository.findByName(category);
                    categories.add(categories1);
                }
            });

        if (categories.isEmpty())
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Categories not found"));
        else {
            movies.setCategories(categories);
            movieRepository.save(movies);
            return ResponseEntity.ok(new MovieResponse(movies));
        }

    }


}
