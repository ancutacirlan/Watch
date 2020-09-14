package com.example.WatchNext.payload.request;

import com.example.WatchNext.model.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieRequest {

    private String title;
    private String trailerURL;
    private String originalSourceUrl;
    private String coverUrl;
    private String imdbld;
    private Float imdbScore;
    private String description;
    private Date releaseDate;
    private List<Category> categories = new ArrayList<>();

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
