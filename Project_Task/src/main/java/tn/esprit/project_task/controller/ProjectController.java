package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.FullProjectResponse;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.service.ProjectImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
//http://localhost:8400/timeforge/swagger-ui/index.html#/
@AllArgsConstructor
public class ProjectController {

    private ProjectImpl projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
    }
    @PutMapping("/modify-project")
    public Project modifyProject(@RequestBody Project project) {
        Project p = projectService.modifyProject(project);
        return p;
    }

}