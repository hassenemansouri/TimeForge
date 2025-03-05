package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.entity.Task;
import tn.esprit.project_task.service.TaskImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
//http://localhost:8400/timeforge/swagger-ui/index.html#/
@AllArgsConstructor
public class TaskController {
    private TaskImpl taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

}
