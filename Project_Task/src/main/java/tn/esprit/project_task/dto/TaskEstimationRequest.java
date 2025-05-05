package tn.esprit.project_task.dto;

import lombok.*;
import tn.esprit.project_task.entity.TaskPriority;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEstimationRequest {
        private TaskPriority priority;
        private Date dueDate;

}
