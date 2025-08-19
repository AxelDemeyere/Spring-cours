package org.app.exo2.director.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.app.exo2.director.model.dto.DirectorRequestDTO;
import org.app.exo2.director.model.dto.DirectorResponseDTO;
import org.app.exo2.director.service.DirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogue/realisateur")
@Tag(name = "Réalisateurs", description = "Gestion des réalisateurs dans le catalogue")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les réalisateurs", description = "Cette méthode permet de récupérer la liste de tous les réalisateurs.")
    public ResponseEntity<List<DirectorResponseDTO>> getAllDirectors() {
        return ResponseEntity.ok(directorService.getAllDirectors());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un réalisateur par ID", description = "Cette méthode permet de récupérer un réalisateur spécifique en utilisant son ID.")
    public ResponseEntity<DirectorResponseDTO> getDirectorById(@PathVariable Long id) {
        return ResponseEntity.ok(directorService.getDirectorById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau réalisateur", description = "Cette méthode permet de créer un nouveau réalisateur en fournissant les détails nécessaires.")
    public ResponseEntity<DirectorResponseDTO> createDirector(@RequestBody DirectorRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(directorService.createDirector(requestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un réalisateur", description = "Cette méthode permet de mettre à jour les informations d'un réalisateur existant en utilisant son ID.")
    public ResponseEntity<DirectorResponseDTO> updateDirector(@PathVariable Long id, @RequestBody DirectorRequestDTO requestDTO) {
        return ResponseEntity.ok(directorService.updateDirector(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un réalisateur", description = "Cette méthode permet de supprimer un réalisateur en utilisant son ID.")
    public ResponseEntity<String> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.ok(String.format("Réalisateur avec l'id %d supprimé", id));
    }
}
