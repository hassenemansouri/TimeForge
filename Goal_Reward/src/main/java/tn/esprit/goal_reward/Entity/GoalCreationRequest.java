package tn.esprit.goal_reward.Entity;

import lombok.*;
import tn.esprit.goal_reward.Entity.Categorie;
import tn.esprit.goal_reward.Entity.CategorieRule;
import tn.esprit.goal_reward.Entity.Goal;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class GoalCreationRequest {
    private Goal goal;
    private List<Categorie> categories; // ✅ PAS juste "Categorie categorie"
}

