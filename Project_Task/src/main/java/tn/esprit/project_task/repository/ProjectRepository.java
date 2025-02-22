package tn.esprit.project_task.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project_task.entity.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
}
