package com.example.WatchNext.controller;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.model.Movie;
import com.example.WatchNext.security.services.CategoryService;
import com.example.WatchNext.security.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;

    @Autowired
    public MovieController(MovieService movieService, CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        var movie = movieService.findMovieById(id).
                map(movie1 ->
                    ResponseEntity.ok().body(movie1)
                )
                .orElseThrow(EntityNotFoundException::new);
        return movie;
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovieById(@PathVariable Long id) {
        var movie = movieService.findMovieById(id).
                map(movie1 -> {
                    movieService.deleteMovieById(movie1.getId());
                    return ResponseEntity.ok("The movie was successfully deleted");
                })
                .orElseThrow(EntityNotFoundException::new);
        return movie;
    }


    @PostMapping("/post")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovies(@RequestBody Movie movieRequest) {
        var val = movieService.findMovieByImdbld(movieRequest.getImdbld())
                .orElse(movieService.saveMovie(movieRequest));
        return ResponseEntity.ok(val);
    }


    @GetMapping("/query")
    public ResponseEntity<?> getMovie(@RequestParam Integer limit, @RequestParam Integer skip,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate) {

        var allMovies = movieService.findAllMovies();

        List<Movie> filterMovies = allMovies
                .stream()
                .filter(movie -> movie.getReleaseDate().after(fromDate) && movie.getReleaseDate().before(toDate))
                .skip(skip)
                .limit(limit)
                .collect(Collectors.toList());

        return ResponseEntity.ok(filterMovies);
    }

}
