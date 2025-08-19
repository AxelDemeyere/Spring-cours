package org.app.exo2.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo2.movie.model.entity.Movie;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRequestDTO {

    private String title;
    private String description;
    private LocalDate releaseDate;
    private Long duration; // in minutes
    private String genre;
    private Long directorId;

    public Movie toEntity() {
        return Movie.builder()
                .title(title)
                .description(description)
                .releaseDate(releaseDate)
                .duration(duration)
                .genre(genre)
                .build();
    }
}
