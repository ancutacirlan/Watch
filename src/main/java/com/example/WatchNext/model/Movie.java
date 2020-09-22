package com.example.WatchNext.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @URL
    @Column(name = "trailer_url", nullable = false, unique = true)
    private String trailerUrl;

    @URL
    @Column(name = "original_source_url", nullable = false, unique = true)
    private String originalSourceUrl;

    @URL
    @Column(name = "cover_url", nullable = false, unique = true)
    private String coverUrl;

    @Column(name = "imdbld", nullable = false)
    @NotNull
    private String imdbld;

    @Column(name = "imdb_score", nullable = false)
    private Float imdbScore;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<Category>();

    public Movie(String title, String trailerUrl, String originalSourceUrl, String coverUrl, String imdbld,
                 Float imdbScore, String description, Date releaseDate) {
        this.title = title;
        this.trailerUrl = trailerUrl;
        this.originalSourceUrl = originalSourceUrl;
        this.coverUrl = coverUrl;
        this.imdbld = imdbld;
        this.imdbScore = imdbScore;
        this.description = description;
        this.releaseDate = releaseDate;
    }


}
