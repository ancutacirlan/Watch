package com.example.WatchNext.model;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.URL;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @URL
    private String trailerURL;
    @URL
    private String originalSourceUrl;
    @URL
    private String coverUrl;

    @NotNull
    String imdbld;
    Float imdbScore;
    String description;

    @NotNull
    Date releaseDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="movies_categories",
            joinColumns = @JoinColumn(name = "movies_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Categories> categories = new ArrayList<>();

    public Movies(String title, String trailerURL, String originalSourceUrl, String coverUrl, String imdbld,
                  Float imdbScore, String description, Date releaseDate) {
        this.title = title;
        this.trailerURL = trailerURL;
        this.originalSourceUrl = originalSourceUrl;
        this.coverUrl = coverUrl;
        this.imdbld = imdbld;
        this.imdbScore = imdbScore;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public Movies() {
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public String getOriginalSourceUrl() {
        return originalSourceUrl;
    }

    public void setOriginalSourceUrl(String originalSourceUrl) {
        this.originalSourceUrl = originalSourceUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getImdbld() {
        return imdbld;
    }

    public void setImdbld(String imdbld) {
        this.imdbld = imdbld;
    }

    public Float getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(Float imdbScore) {
        this.imdbScore = imdbScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

}
