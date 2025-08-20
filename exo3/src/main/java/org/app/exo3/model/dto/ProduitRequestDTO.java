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
public class ProduitRequestDTO {
    private String nom;
    private Double prix;

    public Produit toEntity() {
        return Produit.builder()
                .nom(this.nom)
                .prix(this.prix)
                .build();
    }
}
