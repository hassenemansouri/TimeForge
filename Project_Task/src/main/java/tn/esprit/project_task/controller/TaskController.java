package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.entity.Task;
import tn.esprit.project_task.service.TaskImpl;

import java.util.List;

@RestController
@RequestMapping("/tasks")
//http://localhost:8400/timeforge/swagger-ui/index.html#/
@AllArgsConstructor
public class TaskController {
    private TaskImpl service;

    @PostMapping("/add")
    public Task addTask(Task t) {

        Task task = service.addTask(t);
        return task;
    }

    @GetMapping()
    public List<Task> getAllTasks() {
        List<Task> listTasks =  service.findAllTasks ();
        return listTasks;
    }
    @DeleteMapping("/remove-task/{id-task}")
    public void removeTask(@PathVariable("id-task") String id_task) {

        service.deleteTask(id_task);
    }
    @PutMapping("/modify-task")
    public Task modifyTask(@RequestBody Task t) {
        Task task = service.modifyTask(t);
        return task;
    }
}
