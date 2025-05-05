package tn.esprit.project_task.dto;

import lombok.*;
import tn.esprit.project_task.entity.ProjectCategory;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectEstimationRequest {
    private String description;
    private Date startDate;
    private Date endDate;
    private ProjectCategory category;
}
