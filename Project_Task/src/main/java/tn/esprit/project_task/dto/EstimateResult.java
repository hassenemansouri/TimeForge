package tn.esprit.project_task.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstimateResult {
    private double estimatedHours;
    private String complexity;
    private int suggestedTeamSize;
    private String riskAssessment;
}
