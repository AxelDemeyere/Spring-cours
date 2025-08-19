package org.app.exo2.movie.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo2.director.model.entity.Director;
import org.app.exo2.movie.model.dto.MovieResponseDTO;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Long duration; // in minutes
    private String genre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    public MovieResponseDTO toDTO() {
        return MovieResponseDTO.builder()
                .id(id)
                .title(title)
                .description(description)
                .releaseDate(releaseDate)
                .duration(duration)
                .genre(genre)
                .build();
    }

}
