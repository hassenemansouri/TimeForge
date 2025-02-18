package tn.esprit.goal_reward.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.goal_reward.Entity.Goal;

public interface GoalRepository extends MongoRepository<Goal, Integer> {
}