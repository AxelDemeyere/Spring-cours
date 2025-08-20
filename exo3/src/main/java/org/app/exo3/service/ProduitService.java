package org.app.exo3.service;

import jakarta.persistence.EntityNotFoundException;
import org.app.exo3.model.dto.ProduitRequestDTO;
import org.app.exo3.model.dto.ProduitResponseDTO;
import org.app.exo3.model.entity.Produit;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProduitService {

    public List<ProduitResponseDTO> getAllProduits(Map<Integer, Produit> map) {
        if (map == null) {
            return List.of();
        }
        return map.values().stream()
                .map(Produit::toDTO)
                .toList();
    }

    public ProduitResponseDTO getProduitById(Long id, Map<Integer, Produit> map) {
        if (map == null || !map.containsKey(id.intValue())) {
            throw new EntityNotFoundException("Produit not found with id: " + id);
        }
        return map.get(id.intValue()).toDTO();
    }

    public ProduitResponseDTO createProduit(ProduitRequestDTO requestDTO, Map<Integer, Produit> map) {
        if (map == null) {
            map = new HashMap<>();
        }

        map.put(cpt++, produit);

        return produit.toDTO();
    }

    public ProduitResponseDTO updateProduit(Long id, ProduitRequestDTO requestDTO, Map<Integer, Produit> map) {
        if (map == null || !map.containsKey(id.intValue())) {
            throw new EntityNotFoundException("Produit not found with id: " + id);
        }
        Produit produit = map.get(id.intValue());
        produit.setNom(requestDTO.getNom());
        produit.setPrix(requestDTO.getPrix());
        return produit.toDTO();
    }

    public void deleteProduit(Long id, Map<Integer, Produit> map) {
        if (map == null || !map.containsKey(id.intValue())) {
            throw new EntityNotFoundException("Produit not found with id: " + id);
        }
        map.remove(id.intValue());
    }
}