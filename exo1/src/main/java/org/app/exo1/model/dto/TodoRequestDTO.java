package org.app.exo1.model.dto;

import lombok.*;
import org.app.exo1.model.entity.Todo;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequestDTO {

    private String title;
    private String description;
    private LocalDate date;
    private boolean isCompleted;

    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .description(description)
                .date(date)
                .isCompleted(isCompleted)
                .build();
    }
}
