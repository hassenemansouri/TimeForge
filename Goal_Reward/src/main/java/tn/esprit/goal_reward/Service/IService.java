package tn.esprit.goal_reward.Service;

import tn.esprit.goal_reward.Entity.Goal;
import tn.esprit.goal_reward.FullGoalResponse;

import java.util.List;

public interface IService {

    Goal ajouterGoal(Goal goal);
    List<Goal> getGoals();
    FullGoalResponse findGoalWithUsers(String goalId);
}
