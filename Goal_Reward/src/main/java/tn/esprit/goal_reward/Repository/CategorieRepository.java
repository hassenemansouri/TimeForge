package tn.esprit.goal_reward.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.goal_reward.Entity.Categorie;

public interface CategorieRepository extends MongoRepository<Categorie, String> {
}
