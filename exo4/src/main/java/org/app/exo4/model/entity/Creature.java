package org.app.exo4.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo4.model.dto.CreatureResponseDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Creature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private double weight;
    private boolean dangerous;
    private CreatureType type;

    public CreatureResponseDTO toDTO() {
        return CreatureResponseDTO.builder()
                .id(id)
                .name(name)
                .age(age)
                .weight(weight)
                .dangerous(dangerous)
                .type(type)
                .build();
    }

}
