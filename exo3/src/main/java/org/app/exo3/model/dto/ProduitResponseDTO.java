package org.app.exo3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo3.model.entity.Produit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitResponseDTO {
    private Long id;
    private String nom;
    private Double prix;

    public static ProduitResponseDTO toEntity(Produit produit) {
        return ProduitResponseDTO.builder()
                .id(produit.getId())
                .nom(produit.getNom())
                .prix(produit.getPrix())
                .build();
    }
}
