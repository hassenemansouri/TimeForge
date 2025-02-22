package tn.esprit.goal_reward.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.goal_reward.Entity.Goal;
import tn.esprit.goal_reward.FullGoalResponse;
import tn.esprit.goal_reward.Repository.GoalRepository;
import tn.esprit.goal_reward.Repository.RewardRepository;
import tn.esprit.goal_reward.client.UserClient;

import java.util.List;

@Service
@AllArgsConstructor

public class ServiceImp implements IService {


     private GoalRepository goalRepository;
     private UserClient userClient;



    public Goal ajouterGoal(Goal goal) {

    return goalRepository.save(goal);

    }

    public List<Goal> getGoals() {
        return goalRepository.findAll();
    }


    public FullGoalResponse findGoalWithUsers(String goal_id) {
        var goal = goalRepository.findById(goal_id)
                .orElse( Goal.builder()
                        .title("")
                        .description("")
                        .build());

        var users = userClient.fundAllUsersByGoal(goal_id);

        return FullGoalResponse.builder()
                .title(goal.getTitle())
                .description(goal.getDescription())
                .build();
    }

}

