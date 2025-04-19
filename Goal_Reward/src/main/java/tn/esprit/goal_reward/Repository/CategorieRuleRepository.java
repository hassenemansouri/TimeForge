package tn.esprit.goal_reward.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.goal_reward.Entity.CategorieRule;

public interface CategorieRuleRepository extends MongoRepository<CategorieRule, String> {

}
