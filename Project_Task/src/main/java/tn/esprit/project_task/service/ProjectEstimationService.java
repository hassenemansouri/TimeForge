package tn.esprit.project_task.service;

import org.springframework.stereotype.Service;
import tn.esprit.project_task.dto.EstimateResult;
import tn.esprit.project_task.dto.ProjectEstimationRequest;
import tn.esprit.project_task.entity.ProjectCategory;

import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class ProjectEstimationService {

    private static final Map<ProjectCategory, Double> CATEGORY_COMPLEXITY = Map.of(
            ProjectCategory.DESIGN, 1.2,
            ProjectCategory.SOFTWARE_DEVELOPMENT, 1.8,
            ProjectCategory.MARKETING, 1.0,
            ProjectCategory.FINANCE, 1.0,
            ProjectCategory.RESEARCH, 2.0,
            ProjectCategory.EDUCATION, 1.5,
            ProjectCategory.CONSTRUCTION, 2.2,
            ProjectCategory.HEALTHCARE, 1.6,
            ProjectCategory.OTHER, 1.7
    );

    private static final double BASE_HOURS_PER_WORD = 0.3;
    private static final double MINIMUM_PROJECT_HOURS = 16;

    public EstimateResult estimateProject(ProjectEstimationRequest request) {
        double baseHours = calculateBaseHours(request.getDescription());
        double complexityFactor = CATEGORY_COMPLEXITY.getOrDefault(request.getCategory(), 1.0);
        double adjustedHours = baseHours * complexityFactor;

        long daysBetween = ChronoUnit.DAYS.between(
                request.getStartDate().toInstant(),
                request.getEndDate().toInstant()
        );

        double timelineFactor = calculateTimelineFactor(daysBetween);
        double finalHours = adjustedHours * timelineFactor;
        finalHours = applyLongTermAdjustment(finalHours, daysBetween);

        String complexity = determineComplexity(finalHours, daysBetween);
        int suggestedTeamSize = calculateTeamSize(finalHours, daysBetween, complexity);
        String riskAssessment = assessRisk(finalHours, daysBetween, suggestedTeamSize);

        return new EstimateResult(
                Math.round(finalHours * 10) / 10.0,
                complexity,
                suggestedTeamSize,
                riskAssessment
        );
    }

    private double calculateBaseHours(String description) {
        int wordCount = description.split("\\s+").length;
        int sentenceCount = description.split("[.!?]+").length;
        int paragraphCount = description.split("\\n\\n").length;

        double baseEstimate = wordCount * BASE_HOURS_PER_WORD;
        baseEstimate += sentenceCount * 0.5;
        baseEstimate += paragraphCount * 2;

        String descLower = description.toLowerCase();
        int complexityScore = 0;

        if (descLower.matches(".*\\b(ai|machine learning|deep learning|nlp|data pipeline)\\b.*"))
            complexityScore += 3;
        if (descLower.matches(".*\\b(real-time|scalable|distributed|high availability)\\b.*"))
            complexityScore += 2;
        if (descLower.matches(".*\\b(prototype|research|exploratory|uncertain)\\b.*"))
            complexityScore += 2;
        if (descLower.matches(".*\\b(integration|migration|legacy|refactor)\\b.*"))
            complexityScore += 2;

        baseEstimate *= (1 + 0.05 * complexityScore);

        return Math.max(MINIMUM_PROJECT_HOURS, baseEstimate);
    }

    private double calculateTimelineFactor(long days) {
        if (days < 3) return 2.0;
        if (days < 7) return 1.5;
        if (days < 14) return 1.2;
        if (days < 30) return 1.0;
        if (days < 90) return 0.9;
        if (days < 180) return 0.85;
        return 0.8;
    }

    private double applyLongTermAdjustment(double hours, long days) {
        if (days > 365) return hours * 1.5;
        if (days > 180) return hours * 1.2;
        return hours;
    }

    private String determineComplexity(double hours, long days) {
        double dailyIntensity = hours / days;
        if (hours < 40 && dailyIntensity < 0.5) return "Simple";
        if (hours < 100 && dailyIntensity < 1.0) return "Medium";
        if (hours < 500) return "Complex";
        return "Large-scale";
    }

    private int calculateTeamSize(double hours, long days, String complexity) {
        double personDays = hours / 8;
        double dailyCapacity = personDays / days;
        int baseTeamSize = (int) Math.ceil(dailyCapacity);

        switch (complexity) {
            case "Large-scale": return Math.max(5, baseTeamSize + 2);
            case "Complex": return Math.max(3, baseTeamSize + 1);
            default: return Math.max(1, baseTeamSize);
        }
    }

    private String assessRisk(double hours, long days, int teamSize) {
        double dailyLoad = hours / (days * 8);
        double perPersonLoad = dailyLoad / teamSize;

        if (perPersonLoad > 1.5 || teamSize > 10)
            return "High - Significant risk of delays and budget overruns";
        if (perPersonLoad > 1.0 || teamSize > 5)
            return "Medium - Requires careful management";
        if (days > 365)
            return "Medium - Long duration increases uncertainty";
        return "Low - Manageable workload";
    }
}