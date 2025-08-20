package org.app.exo4.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo4.model.entity.Creature;
import org.app.exo4.model.entity.CreatureType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatureRequestDTO {

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String name;

    @Min(value = 0, message = "L'âge doit être positif")
    @Max(value = 10000, message = "L'âge ne peut pas dépasser 10000 ans")
    private int age;

    @DecimalMin(value = "0.1", message = "Le poids doit être supérieur à 0.1 kg")
    @DecimalMax(value = "50000.0", message = "Le poids ne peut pas dépasser 50000 kg")
    private double weight;

    private boolean dangerous;

    @NotNull(message = "Le type de créature est obligatoire")
    private CreatureType type;

    public Creature toEntity() {
        return Creature.builder()
                .name(name)
                .age(age)
                .weight(weight)
                .dangerous(dangerous)
                .type(type)
                .build();
    }
}