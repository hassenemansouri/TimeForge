package tn.esprit.goal_reward.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.goal_reward.Entity.Categorie;
import tn.esprit.goal_reward.Entity.CategorieRule;

import java.util.Optional;

public interface CategorieRepository extends MongoRepository<Categorie, String> {

}
