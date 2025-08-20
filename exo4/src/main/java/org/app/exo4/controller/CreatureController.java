package org.app.exo4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.app.exo4.model.dto.CreatureRequestDTO;
import org.app.exo4.model.dto.CreatureResponseDTO;
import org.app.exo4.model.entity.CreatureType;
import org.app.exo4.service.CreatureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creatures")
@Tag(name = "Créatures", description = "Gestion des créatures")
public class CreatureController {

    private final CreatureService creatureService;

    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    @GetMapping
    @Operation(summary = "Récupérer toutes les créatures", description = "Cette méthode permet de récupérer la liste de toutes les créatures.")
    public ResponseEntity<List<CreatureResponseDTO>> getAllCreatures() {
        return ResponseEntity.ok(creatureService.getAllCreatures());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une créature par ID", description = "Cette méthode permet de récupérer une créature spécifique en utilisant son ID.")
    public ResponseEntity<CreatureResponseDTO> getCreatureById(@PathVariable Long id) {
        return ResponseEntity.ok(creatureService.getCreatureById(id));
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle créature", description = "Cette méthode permet de créer une nouvelle créature en fournissant les détails nécessaires.")
    public ResponseEntity<CreatureResponseDTO> createCreature(@Valid @RequestBody CreatureRequestDTO creatureRequestDTO) {
        return ResponseEntity.status(201).body(creatureService.createCreature(creatureRequestDTO.toEntity()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une créature", description = "Cette méthode permet de mettre à jour les informations d'une créature existante en utilisant son ID.")
    public ResponseEntity<CreatureResponseDTO> updateCreature(@PathVariable Long id, @Valid @RequestBody CreatureRequestDTO creatureRequestDTO) {
        return ResponseEntity.ok(creatureService.updateCreature(id, creatureRequestDTO.toEntity()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une créature", description = "Cette méthode permet de supprimer une créature en utilisant son ID.")
    public ResponseEntity<Void> deleteCreature(@PathVariable Long id) {
        creatureService.deleteCreature(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    @Operation(summary = "Récupérer les créatures paginées avec filtres",
            description = "Cette méthode permet de récupérer les créatures avec pagination et filtres optionnels.")
    public Page<CreatureResponseDTO> getPagedCreatures(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) CreatureType type,
            @RequestParam(required = false) Boolean dangerous,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) Double minWeight,
            @RequestParam(required = false) Double maxWeight,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortField).descending() :
                Sort.by(sortField).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return creatureService.getFilteredPage(
                name, type, dangerous, minAge, maxAge, minWeight, maxWeight, pageable
        );
    }
}