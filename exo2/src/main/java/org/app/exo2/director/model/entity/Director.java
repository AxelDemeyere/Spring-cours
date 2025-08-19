package org.app.exo2.director.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo2.director.model.dto.DirectorResponseDTO;
import org.app.exo2.movie.model.entity.Movie;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String nationality;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("releaseDate DESC")
    private List<Movie> movies;

    public DirectorResponseDTO toDTO() {
        return DirectorResponseDTO.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .nationality(nationality)
                .movies(movies != null ? movies.stream().map(Movie::toDTO).toList() : null)
                .build();
    }
}
