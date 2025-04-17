package tn.esprit.user_strategicpartership.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;


public interface StrategicPartnershipRepository extends
    MongoRepository<StrategicPartnertship, String> {
  boolean existsByName(String name);

}
