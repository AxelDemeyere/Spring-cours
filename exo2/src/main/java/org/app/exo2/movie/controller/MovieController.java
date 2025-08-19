package org.app.exo2.movie.controller;

import org.app.exo2.movie.model.dto.MovieRequestDTO;
import org.app.exo2.movie.model.dto.MovieResponseDTO;
import org.app.exo2.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogue/film")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/realisateur/{directorId}")
    public ResponseEntity<List<MovieResponseDTO>> getMoviesByDirectorId(@PathVariable Long directorId) {
        return ResponseEntity.ok(movieService.getMoviesByDirectorId(directorId));
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(movieService.createMovie(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO requestDTO) {
        return ResponseEntity.ok(movieService.updateMovie(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(String.format("Film avec l'id %d supprimé", id));
    }
}
