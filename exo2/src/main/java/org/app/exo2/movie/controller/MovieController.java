package org.app.exo2.movie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.exo2.movie.model.dto.MovieRequestDTO;
import org.app.exo2.movie.model.dto.MovieResponseDTO;
import org.app.exo2.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogue/film")
@Tag(name = "Films", description = "Gestion des films dans le catalogue")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les films", description = "Cette méthode permet de récupérer la liste de tous les films.")
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un film par ID", description = "Cette méthode permet de récupérer un film spécifique en utilisant son ID.")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/realisateur/{directorId}")
    @Operation(summary = "Récupérer les films par ID de réalisateur", description = "Cette méthode permet de récupérer tous les films réalisés par un réalisateur spécifique en utilisant son ID.")
    public ResponseEntity<List<MovieResponseDTO>> getMoviesByDirectorId(@PathVariable Long directorId) {
        return ResponseEntity.ok(movieService.getMoviesByDirectorId(directorId));
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau film", description = "Cette méthode permet de créer un nouveau film en fournissant les détails nécessaires.")
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(movieService.createMovie(requestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un film", description = "Cette méthode permet de mettre à jour les informations d'un film existant en utilisant son ID.")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO requestDTO) {
        return ResponseEntity.ok(movieService.updateMovie(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un film", description = "Cette méthode permet de supprimer un film en utilisant son ID.")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(String.format("Film avec l'id %d supprimé", id));
    }
}
