package tn.esprit.project_task.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.project_task.client.UserClient;
import tn.esprit.project_task.entity.Task;
import tn.esprit.project_task.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

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
        return taskRepository.save(task);
    }

    public void deleteTask(String id_task) {
        taskRepository.deleteById(id_task);
    }

}
