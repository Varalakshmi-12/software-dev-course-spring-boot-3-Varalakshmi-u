package com.example.springBoot2.controllers;

import com.example.springBoot2.models.Album;
import com.example.springBoot2.models.Movie;
import com.example.springBoot2.repositories.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getMovies(){
        return  movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id){
        return movieRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @PostMapping("/add")
    public List<Movie> addBulkMovie(@RequestBody List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable int id , @RequestBody Movie movie){

        Movie movie1 = movieRepository.findById(id).orElse(null);
        movie1.setName(movie.getName());
        movie1.setDirector(movie.getDirector());
        movie1.setYear(movie.getYear());
        movie1.setRuntime(movie.getRuntime());
        return movieRepository.save(movie1);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){
        movieRepository.deleteById(id);
    }
}