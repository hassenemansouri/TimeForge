package tn.esprit.project_task.dto;

import lombok.Builder;
import lombok.Data;
import tn.esprit.project_task.entity.Project;

@Data
@Builder
public class BoardDTO {
        private String title;
        private String description;
        private Project project;

}
