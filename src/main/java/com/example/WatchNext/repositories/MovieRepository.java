package com.example.WatchNext.repositories;

import com.example.WatchNext.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movies, Long> {

    Boolean existsByImdbld(String imdbld);

    Movies findByImdbld(String imdbld);

    Optional<?> findByReleaseDateAfterAndReleaseDateBefore(Date releaseDate, Date releaseDate2);
    List<Movies> findByReleaseDateAfter(Date releaseDate);

}
