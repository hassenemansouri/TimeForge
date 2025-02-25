package tn.esprit.goal_reward.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.goal_reward.Entity.Goal;
import tn.esprit.goal_reward.Entity.Reward;
import tn.esprit.goal_reward.FullGoalResponse;
import tn.esprit.goal_reward.Repository.GoalRepository;
import tn.esprit.goal_reward.Repository.RewardRepository;
import tn.esprit.goal_reward.client.UserClient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ServiceImp implements IService {


     private GoalRepository goalRepository;
     private UserClient userClient;
     private RewardRepository rewardRepository;



    public Goal ajouterGoal(Goal goal) {
        if (goal == null || goal.getTitle() == null || goal.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Le titre du goal est obligatoire.");
        }

        if (goal.getDescription() == null || goal.getDescription().isEmpty()) {
            throw new IllegalArgumentException("La description du goal est obligatoire.");
        }

        return goalRepository.save(goal);
    }

    public List<Goal> getGoals() {
        return goalRepository.findAll();
    }


    public void supprimerGoal(String id) {
        goalRepository.deleteById(id);
    }


    public Goal modifierGoal(String id, Goal goal) {
        if (goalRepository.existsById(id)) {
            goal.setGoal_id(id); // Assigner l'ID existant
            return goalRepository.save(goal);
        }
        return null; // Ou lever une exception
    }
    public Optional<Goal> getGoalById(String id) {
        return goalRepository.findById(id);
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
                .users ( users )
                .build();
    }

    public Reward ajouterReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public void supprimerReward(String id) {
        rewardRepository.deleteById(id);
    }

    public Reward modifierReward(String id, Reward reward) {
        if (rewardRepository.existsById(id)) {
            reward.setId(id);
            return rewardRepository.save(reward);
        }
        return null; // Ou lever une exception
    }

    public Optional<Reward> getRewardById(String id) {
        return rewardRepository.findById(id);
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

}

