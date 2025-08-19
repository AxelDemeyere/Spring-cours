package org.app.exo2.director.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo2.director.model.entity.Director;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorRequestDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String nationality;

     public Director toEntity() {
         return Director.builder()
                 .firstName(firstName)
                 .lastName(lastName)
                 .birthDate(birthDate)
                 .nationality(nationality)
                 .build();
     }
}
