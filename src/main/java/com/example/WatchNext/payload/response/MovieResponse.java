package com.example.WatchNext.payload.response;

import com.example.WatchNext.model.Movie;

public class MovieResponse {

    private Movie movies;

    public MovieResponse(Movie movies) {
        this.movies = movies;
    }

    public Movie getMovies() {
        return movies;
    }

    public void setMovies(Movie movies) {
        this.movies = movies;
    }

}
