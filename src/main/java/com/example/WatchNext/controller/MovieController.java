package com.example.WatchNext.controller;

import com.example.WatchNext.model.Movie;
import com.example.WatchNext.payload.request.MovieRequest;
import com.example.WatchNext.security.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        var movie = movieService.findMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
    }


    @PostMapping("/post")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovies(@RequestBody MovieRequest movieRequest) {
        var val = movieService.findMovieByImdbld(movieRequest.getImdbld())
                .orElse(movieService.saveMovie(movieRequest));
        return ResponseEntity.ok(val);
    }


    @GetMapping("/query")
    public ResponseEntity<?> getMovie(@RequestParam Integer limit, @RequestParam Integer skip,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate) {

        List<Movie> allMovies = movieService.findAllMovies();

        List<Movie> filterMovies = allMovies
                .stream()
                .filter(movie -> movie.getReleaseDate().after(fromDate) && movie.getReleaseDate().before(toDate))
                .skip(skip)
                .limit(limit)
                .collect(Collectors.toList());

        return ResponseEntity.ok(filterMovies);
    }

}
