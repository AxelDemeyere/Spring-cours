package org.app.exo2.director.service;

import org.app.exo2.director.model.dto.DirectorRequestDTO;
import org.app.exo2.director.model.dto.DirectorResponseDTO;
import org.app.exo2.director.model.entity.Director;
import org.app.exo2.director.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public List<DirectorResponseDTO> getAllDirectors() {
        return directorRepository.findAll().stream()
                .map(Director::toDTO)
                .toList();
    }

    public DirectorResponseDTO getDirectorById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réalisateur non trouvé avec l'id: " + id));
        return director.toDTO();
    }

    public DirectorResponseDTO createDirector(DirectorRequestDTO requestDTO) {
        Director director = requestDTO.toEntity();
        return directorRepository.save(director).toDTO();
    }

    public DirectorResponseDTO updateDirector(Long id, DirectorRequestDTO requestDTO) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réalisateur non trouvé avec l'id: " + id));

        director.setFirstName(requestDTO.getFirstName());
        director.setLastName(requestDTO.getLastName());
        director.setBirthDate(requestDTO.getBirthDate());
        director.setNationality(requestDTO.getNationality());

        return directorRepository.save(director).toDTO();
    }

    public void deleteDirector(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new RuntimeException("Réalisateur non trouvé avec l'id: " + id);
        }
        directorRepository.deleteById(id);
    }
}
