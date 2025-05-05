package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.dto.EstimateResult;
import tn.esprit.project_task.dto.ProjectEstimationRequest;
import tn.esprit.project_task.dto.TaskEstimationRequest;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.entity.Task;
import tn.esprit.project_task.entity.TaskEstimation;
import tn.esprit.project_task.repository.TaskRepository;
import tn.esprit.project_task.service.TaskEstimationService;
import tn.esprit.project_task.service.TaskImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
//http://localhost:8400/timeforge/swagger-ui/index.html#/
@AllArgsConstructor
public class TaskController {
    private TaskImpl taskService;
    private TaskEstimationService taskEstimationService;
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task) ;
    }


    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
    @PutMapping("/update/{id}")
    public Task update(@PathVariable String id, @RequestBody Task task) {
        return taskService.update(id, task);
    }
    @GetMapping("/column/{columnId}")
    public List<Task> getTasksByColumnId(@PathVariable String columnId) {
        return taskService.getTasksByColumnId(columnId);
    }
    @PostMapping("/estimate")
    public TaskEstimation estimateTask(@RequestBody TaskEstimationRequest request) {
        return taskEstimationService.estimateTask(request);
    }
    @GetMapping("/{id}/estimate")
    public TaskEstimation estimateExistingTask(@PathVariable String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            // Retourne null, ou vous pouvez créer un TaskEstimation par défaut ou lever une RuntimeException simple
            return null; // ou new TaskEstimation(0, "Task not found", 0, "N/A");
        }

        Task task = optionalTask.get();

        TaskEstimationRequest request = new TaskEstimationRequest();
        request.setPriority(task.getPriority());
        request.setDueDate(task.getDueDate());

        return taskEstimationService.estimateTask(request);
    }
    @GetMapping("/projects/{id}")
    public List<Task> getTasksByProject(@PathVariable Project id) {
        return taskService.getTasksByProject(id);
    }


}
