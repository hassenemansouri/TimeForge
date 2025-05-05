package tn.esprit.project_task.service;

import lombok.AllArgsConstructor;


import org.springframework.stereotype.Service;
import tn.esprit.project_task.client.UserClient;

import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.entity.Task;
import tn.esprit.project_task.repository.TaskRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class TaskImpl implements IServiceTask{
    private TaskRepository taskRepository;
    private UserClient userClient;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id_task) {
        return taskRepository.findById(id_task);
    }

    public Task createTask(Task task) {
        task.setCreatedAt(new Date());
        task.setHistory(task.getHistory() != null ? task.getHistory() : new ArrayList<>());
        return taskRepository.save(task);
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

    public List<Task> getTasksByProject(Project project) {
        return taskRepository.findByProject(project);
    }

}
