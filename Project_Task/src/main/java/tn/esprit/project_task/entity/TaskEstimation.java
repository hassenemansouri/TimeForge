package tn.esprit.project_task.entity;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEstimation {
    private double urgencyScore;
    private String recommendedAction;
    private double completionProbability;
    private String timeEstimate;
}
