package tn.esprit.project_task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDTO {
        private String title;
        private String description;
}
