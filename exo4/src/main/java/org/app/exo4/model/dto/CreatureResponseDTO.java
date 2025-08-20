package org.app.exo4.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo4.model.entity.CreatureType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatureResponseDTO {

    private Long id;
    private String name;
    private int age;
    private double weight;
    private boolean dangerous;
    private CreatureType type;
}
