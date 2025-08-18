package org.app.exo1.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private boolean isCompleted;

}
