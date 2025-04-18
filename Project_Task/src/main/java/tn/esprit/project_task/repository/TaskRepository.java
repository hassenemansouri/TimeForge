package tn.esprit.project_task.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project_task.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByColumnId(String columnId);
   // List<Task> findByProjectId(String projectId);


}
