package com.example.WatchNext.payload.response;

import com.example.WatchNext.model.Movies;

public class MovieResponse {

    private Movies movies;

    public MovieResponse(Movies movies) {
        this.movies = movies;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }
}
