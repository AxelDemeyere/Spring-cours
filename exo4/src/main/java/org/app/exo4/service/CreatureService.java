package org.app.exo4.service;

import org.app.exo4.model.dto.CreatureResponseDTO;
import org.app.exo4.model.entity.Creature;
import org.app.exo4.model.entity.CreatureType;
import org.app.exo4.repository.CreatureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatureService {

    private final CreatureRepository creatureRepository;

    public CreatureService(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    public List<CreatureResponseDTO> getAllCreatures() {
        return creatureRepository.findAll().stream().map(Creature::toDTO).toList();
    }

    public CreatureResponseDTO getCreatureById(Long id) {
        Creature creature = creatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Créature non trouvée avec l'id: " + id));
        return creature.toDTO();
    }

    public CreatureResponseDTO createCreature(Creature creature) {
        return creatureRepository.save(creature).toDTO();
    }

    public CreatureResponseDTO updateCreature(Long id, Creature creature) {
        Creature existingCreature = creatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Créature non trouvée avec l'id: " + id));

        existingCreature.setName(creature.getName());
        existingCreature.setAge(creature.getAge());
        existingCreature.setWeight(creature.getWeight());
        existingCreature.setDangerous(creature.isDangerous());
        existingCreature.setType(creature.getType());

        return creatureRepository.save(existingCreature).toDTO();
    }

    public void deleteCreature(Long id) {
        if (!creatureRepository.existsById(id)) {
            throw new RuntimeException("Créature non trouvée avec l'id: " + id);
        }
        creatureRepository.deleteById(id);
    }

    public Page<CreatureResponseDTO> getPage(Pageable pageable) {
        return creatureRepository.findAll(pageable).map(Creature::toDTO);
    }

    public Page<CreatureResponseDTO> getFilteredPage(
            String name, CreatureType type, Boolean dangerous,
            Integer minAge, Integer maxAge, Double minWeight, Double maxWeight,
            Pageable pageable) {
        return creatureRepository.findWithFilters(
                name, type, dangerous, minAge, maxAge, minWeight, maxWeight, pageable
        ).map(Creature::toDTO);
    }
}