package tn.esprit.project_task;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponse {
    @Id
    @Column(name = "board_id")
    private String id_board;
    @NotNull(message = "Project title cannot be null")
    @Size(min = 3, max = 50, message = "Project title must be between 3 and 50 characters")
    private String title;

    @NotNull(message = "Project description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;
}
