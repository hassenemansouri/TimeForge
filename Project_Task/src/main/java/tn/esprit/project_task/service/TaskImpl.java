package tn.esprit.project_task.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import tn.esprit.project_task.client.UserClient;
import tn.esprit.project_task.entity.Task;
import tn.esprit.project_task.entity.TaskPriority;
import tn.esprit.project_task.repository.TaskRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskImpl implements IServiceTask{
    private TaskRepository taskRepository;
    private UserClient userClient;
    private final MongoTemplate mongoTemplate;
    @Autowired
    public TaskImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id_task) {
        return taskRepository.findById(id_task);
    }

    public Task createTask(Task task) {
        task.setCreatedAt(new Date());
        task.setHistory(task.getHistory() != null ? task.getHistory() : new ArrayList<>());
        return taskRepository.save(task); // Très important : retourne l’objet sauvegardé
    }

    public void deleteTask(String id_task) {
        taskRepository.deleteById(id_task);
    }
    public Task update(String id, Task task) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.set_id(id);
        return taskRepository.save(task);
    }
    public List<Task> getTasksByColumnId(String columnId) {

        return taskRepository.findByColumnId(columnId);
    }
//    public List<Task> searchTasks(String name, String priority, String assignedToId, String projectId) {
//        Query query = new Query();
//
//        if (name != null && !name.isEmpty()) {
//            query.addCriteria(Criteria.where("name").regex(name, "i")); // i = ignore case
//        }
//        if (priority != null && !priority.isEmpty()) {
//            query.addCriteria(Criteria.where("priority").is(TaskPriority.valueOf(priority)));
//        }
//        if (assignedToId != null && !assignedToId.isEmpty()) {
//            query.addCriteria(Criteria.where("assignedTo.$id").is(assignedToId));
//        }
//        if (projectId != null && !projectId.isEmpty()) {
//            query.addCriteria(Criteria.where("project.$id").is(projectId));
//        }
//
//        return mongoTemplate.find(query, Task.class);
//    }

//    @Override
//    public List<Task> getTasksByProjectId(String projectId) {
//        return taskRepository.findByProjectId(projectId);
//    }
}
