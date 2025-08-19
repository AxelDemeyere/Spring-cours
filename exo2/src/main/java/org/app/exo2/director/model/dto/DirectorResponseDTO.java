package org.app.exo2.director.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo2.movie.model.dto.MovieResponseDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String nationality;
    private List<MovieResponseDTO> movies;

}
