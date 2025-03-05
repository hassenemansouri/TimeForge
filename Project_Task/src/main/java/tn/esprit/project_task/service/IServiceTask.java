package tn.esprit.project_task.service;

import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.entity.Task;

import java.util.List;
import java.util.Optional;

public interface IServiceTask {
    Task createTask(Task task) ;
    List<Task> getAllTasks();
    void deleteTask(String id_task);
    Optional<Task> getTaskById(String id_task);
    Task modifyTask(Task task);

}
