package tn.esprit.project_task.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project_task.entity.Sprint;

@Repository

public interface SprintRepository extends MongoRepository<Sprint, String> {
}
