package tn.esprit.goal_reward.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.goal_reward.Entity.Reward;

public interface RewardRepository extends MongoRepository<Reward, String> {
}