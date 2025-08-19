package org.app.exo2.director.controller;

import org.app.exo2.director.model.dto.DirectorRequestDTO;
import org.app.exo2.director.model.dto.DirectorResponseDTO;
import org.app.exo2.director.service.DirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogue/realisateur")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<List<DirectorResponseDTO>> getAllDirectors() {
        return ResponseEntity.ok(directorService.getAllDirectors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> getDirectorById(@PathVariable Long id) {
        return ResponseEntity.ok(directorService.getDirectorById(id));
    }

    @PostMapping
    public ResponseEntity<DirectorResponseDTO> createDirector(@RequestBody DirectorRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(directorService.createDirector(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> updateDirector(@PathVariable Long id, @RequestBody DirectorRequestDTO requestDTO) {
        return ResponseEntity.ok(directorService.updateDirector(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.ok(String.format("Réalisateur avec l'id %d supprimé", id));
    }
}
