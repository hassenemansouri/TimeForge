package tn.esprit.project_task.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project_task.entity.Board;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.entity.Task;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}
