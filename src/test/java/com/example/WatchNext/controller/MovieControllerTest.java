package com.example.WatchNext.controller;

import com.example.WatchNext.model.Category;
import com.example.WatchNext.model.Movie;
import com.example.WatchNext.security.services.CategoryService;
import com.example.WatchNext.security.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerTest {

    private MockMvc mvc;

    @Mock
    private MovieService movieService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(movieService).build();
    }

    @Test
    void getMovieById() throws Exception {
        Movie testMovie = new Movie("Insurgent", "https://www.youtube.com/watch?v=sutigsWjz21w0sM",
                "https:s//www.imdb.com/title/tt148s400w309/",
                "https://www.googles.com/search?q=diverge5nt+cover&ssa=X&hl=ro&sxwsrf=ALeKk03UCwSZQOLD8pq5ozzzzg" +
                        "YCCKgyyH6w:1599645634098&tbm=isch&source=iu&ictx=1&fir=X-ld8LLi5QBU-M%252CLTHoQe8cXB93cM%252C_" +
                        "&vet=1&usg=AI4_-kRdFxBl83QQC8i5sj-X0ERGygFNdbg&ved=2ahUKEwilpKWu6NvrAwhWvw4sKHdsxD1MQ9QF6BAgKE" +
                        "EU&biw=1920&bih=976#imgrc=X-ld8LLi5QBU-M",
                "Insurgent (2014)", 4.6f, "In a world " +
                "divided by factions based on virtues, Tris learns she's Divergent and won't fit in. When she discovers" +
                " a plot to destroy Divergents, Tris and the mysterious Four must find out what makes Divergents " +
                "dangerous before it's too late.", new Date(2010,12,3));
        testMovie.setId(1L);
        Category category = new Category("Drama");
        category.setId(1L);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        testMovie.setCategories(categories);
        when(movieService.findMovieById(any(Long.class))).thenReturn(Optional.of(testMovie));
        //when(categoryService.findCategoryById(any(Long.class))).thenReturn(Optional.of(category));

        mvc.perform(MockMvcRequestBuilders.get("/api/movie/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(testMovie.getTitle())));

    }

    @Test
    void deleteMovieById() throws Exception {
        Movie movie = mock(Movie.class);
        movieService.deleteMovieById(movie.getId());
        mvc.perform(MockMvcRequestBuilders.delete("/api/movie/delete/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("The movie was successfully deleted"));
    }

    @Test
    void addMovies() throws Exception {
        //Movie movie = mock(Movie.class);
        Movie testMovie = new Movie("Insurgent", "https://www.youtube.com/watch?v=sutigsWjz21w0sM",
                "https:s//www.imdb.com/title/tt148s400w309/",
                "https://www.googles.com/search?q=diverge5nt+cover&ssa=X&hl=ro&sxwsrf=ALeKk03UCwSZQOLD8pq5ozzzzg" +
                        "YCCKgyyH6w:1599645634098&tbm=isch&source=iu&ictx=1&fir=X-ld8LLi5QBU-M%252CLTHoQe8cXB93cM%252C_" +
                        "&vet=1&usg=AI4_-kRdFxBl83QQC8i5sj-X0ERGygFNdbg&ved=2ahUKEwilpKWu6NvrAwhWvw4sKHdsxD1MQ9QF6BAgKE" +
                        "EU&biw=1920&bih=976#imgrc=X-ld8LLi5QBU-M",
                "Insurgent (2014)", 4.6f, "In a world " +
                "divided by factions based on virtues, Tris learns she's Divergent and won't fit in. When she discovers" +
                " a plot to destroy Divergents, Tris and the mysterious Four must find out what makes Divergents " +
                "dangerous before it's too late.", new Date(2010,12,3));
        testMovie.setId(1L);
        Category category = new Category("Drama");
        category.setId(1L);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        testMovie.setCategories(categories);
        when(movieService.saveMovie(testMovie)).thenReturn(testMovie);
        mvc.perform(MockMvcRequestBuilders.post("/api/movie/post"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(testMovie.toString()));

    }

}