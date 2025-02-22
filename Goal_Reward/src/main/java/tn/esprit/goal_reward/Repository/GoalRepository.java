package tn.esprit.goal_reward.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.goal_reward.Entity.Goal;

@Repository
public interface GoalRepository extends MongoRepository<Goal, String> {
}