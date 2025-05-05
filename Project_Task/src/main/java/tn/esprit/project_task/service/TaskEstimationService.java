package tn.esprit.project_task.service;

import org.springframework.stereotype.Service;
import tn.esprit.project_task.entity.TaskEstimation;
import tn.esprit.project_task.dto.TaskEstimationRequest;
import tn.esprit.project_task.entity.TaskPriority;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Service
public class TaskEstimationService {

    private static final Map<TaskPriority, Double> PRIORITY_WEIGHTS = Map.of(
            TaskPriority.URGENT, 2.5,
            TaskPriority.HIGH, 1.5,
            TaskPriority.MEDIUM, 1.0,
            TaskPriority.LOW, 0.7
    );

    public TaskEstimation estimateTask(TaskEstimationRequest request) {
        // Mock AI calculation (replace with actual model)
        long daysUntilDue = calculateDaysUntilDue(request.getDueDate());

        double baseScore = calculateBaseScore(daysUntilDue);
        double priorityMultiplier = PRIORITY_WEIGHTS.get(request.getPriority());
        double urgencyScore = baseScore * priorityMultiplier;

        return new TaskEstimation(
                urgencyScore,
                determineRecommendedAction(urgencyScore, daysUntilDue),
                calculateCompletionProbability(urgencyScore),
                estimateTimeRequired(request.getPriority(), daysUntilDue)
        );
    }

    private long calculateDaysUntilDue(Date dueDate) {
        return ChronoUnit.DAYS.between(
                LocalDate.now(),
                dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        );
    }

    private double calculateBaseScore(long daysUntilDue) {
        return Math.max(1, 10 - daysUntilDue) * 0.5;
    }

    private String determineRecommendedAction(double score, long days) {
        if (score > 9) return "Immediate Action - Critical Priority";
        if (score > 7) return "Urgent - Handle Within 24 Hours";
        if (score > 4) return "Schedule This Week";
        return "Plan Accordingly";
    }

    private double calculateCompletionProbability(double score) {
        return Math.min(95, Math.max(5, score * 10));
    }

    private String estimateTimeRequired(TaskPriority priority, long days) {
        int baseHours = switch (priority) {
            case URGENT -> 12;
            case HIGH -> 8;
            case MEDIUM -> 4;
            case LOW -> 2;
        };
        return baseHours + "-" + (baseHours + 2) + " hours";
    }
}