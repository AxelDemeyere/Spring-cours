package org.app.exo1.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.exo1.model.dto.TodoResponseDTO;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private boolean isCompleted;

    public TodoResponseDTO toDto() {
        return TodoResponseDTO.builder()
                .id(id)
                .title(title)
                .description(description)
                .date(date)
                .isCompleted(isCompleted)
                .build();
    }

}
