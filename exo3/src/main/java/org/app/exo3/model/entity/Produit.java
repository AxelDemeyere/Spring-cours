package org.app.exo3.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo3.model.dto.ProduitResponseDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produit {
    private Long id;
    private String nom;
    private Double prix;


    public ProduitResponseDTO toDTO() {
        return ProduitResponseDTO.builder()
                .id(this.id)
                .nom(this.nom)
                .prix(this.prix)
                .build();
    }
}
