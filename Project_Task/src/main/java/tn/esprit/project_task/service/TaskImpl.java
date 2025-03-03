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
    @Override
    public Task addTask(Task task) {

        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAllTasks() {

        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(String id_task) {
        taskRepository.deleteById(id_task);

    }

    @Override
    public Task modifyTask(Task task) {

        return taskRepository.save(task);

    }
    public Optional<Task> getTaskById(String TaskId) {
        return taskRepository.findById(TaskId);
    }

}
