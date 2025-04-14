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

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.createTask(task);
        return ResponseEntity.ok(savedTask); // Assure que le JSON retourné contient bien l’_id
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
//    @GetMapping("/search")
//    public List<Task> searchTasks(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String priority,
//            @RequestParam(required = false) String assignedToId,
//            @RequestParam(required = false) String projectId
//    ) {
//        return taskService.searchTasks(name, priority, assignedToId, projectId);
//   }

//    @GetMapping("/project/{projectId}")
//    public List<Task> getTasksByProjectId(@PathVariable String projectId) {
//        return taskService.getTasksByProjectId(projectId);
//    }
}
