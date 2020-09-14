package com.example.WatchNext.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieRequest {

    private String title;
    private String trailerURL;
    private String originalSourceUrl;
    private String coverUrl;
    private String imdbld;
    private Float imdbScore;
    private String description;
    private Date releaseDate;
    private List<String> categories = new ArrayList<>();

}
