package org.app.exo2.movie.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo2.director.model.dto.DirectorResponseDTO;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Long duration; // in minutes
    private String genre;

    @JsonIgnore
    private DirectorResponseDTO director;
}
