package tn.esprit.goal_reward.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.goal_reward.Entity.Goal;
import tn.esprit.goal_reward.Repository.GoalRepository;
import tn.esprit.goal_reward.Repository.RewardRepository;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ServiceImp implements IService {

    @Autowired
    GoalRepository goalRepository;
    @Autowired
    RewardRepository rewardRepository;

public Goal ajouterGoal(Goal goal) {
    return goalRepository.save(goal);
}
}
