package tn.esprit.goal_reward.Entity;

import lombok.*;
import tn.esprit.goal_reward.Entity.Categorie;
import tn.esprit.goal_reward.Entity.CategorieRule;
import tn.esprit.goal_reward.Entity.Goal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalCreationRequest {
    private Goal goal;
    private Categorie categorie;
    private CategorieRule rule;
}
