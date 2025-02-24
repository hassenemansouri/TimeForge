package tn.esprit.project_task.service;

import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.entity.Task;

import java.util.List;

public interface IServiceTask {
    Task addTask(Task task);
    List<Task> findAllTasks();
    void deleteTask(String id_task);
    Task modifyTask(Task task);
}
