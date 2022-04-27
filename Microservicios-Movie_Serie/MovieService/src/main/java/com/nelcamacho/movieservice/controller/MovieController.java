package com.nelcamacho.movieservice.controller;

import com.nelcamacho.movieservice.models.Movie;
import com.nelcamacho.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    /* ============================ Attributes ============================= */

    private final MovieService movieService;

    /* ============================ Methods ================================= */
    /* =============== [GET] ====================== */

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.getById(id));
    }

    @GetMapping("/genres/{genre}")
    public ResponseEntity<List<Movie>> findAllByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(movieService.getListByGenre(genre));
    }

    @GetMapping("/fallback")
    public ResponseEntity<?> fallback() {
        Movie fallbackMovie = new Movie();
        fallbackMovie.setId(0L);
        fallbackMovie.setName("Movie without content");
        fallbackMovie.setGenre("Unknown");
        fallbackMovie.setUrlStream("https://cdn.dribbble.com/users/1249496/screenshots/14000121/media/47911b2981d74902155919dc58762d01.gif");
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(fallbackMovie);
    }

    /* =============== [POST] ====================== */

    @PostMapping
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movie));
    }

    /* =============== [PUT] ====================== */
    /* =============== [DELETE] ====================== */

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    /* ============================ Constructors ============================= */

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

}
