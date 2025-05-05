package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.dto.EstimateResult;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.dto.ProjectEstimationRequest;
import tn.esprit.project_task.entity.Task;
import tn.esprit.project_task.repository.ProjectRepository;
import tn.esprit.project_task.service.ProjectEstimationService;
import tn.esprit.project_task.service.ProjectImpl;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
//http://localhost:8400/timeforge/swagger-ui/index.html#/
@AllArgsConstructor
public class ProjectController {

    private final ProjectImpl projectService;
    private final ProjectEstimationService estimationService;
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @PostMapping("/create")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
    }

    @PutMapping("/update")
    public Project updateProject(@RequestBody Project project) {
        return projectService.update(project);
    }
    @GetMapping("/dashboard-stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        return ResponseEntity.ok(projectService.getDashboardStats());
    }


    @GetMapping("/{id}/tasks")
    public List<Task> getTasksByProject(@PathVariable String id) {
        return projectService.getTaskProjects(id);
    }

    @PostMapping("/estimate")
    public EstimateResult estimateProject(@RequestBody ProjectEstimationRequest request) {
        return estimationService.estimateProject(request);
    }

    @GetMapping("/{id}/estimate")
    public EstimateResult estimateExistingProject(@PathVariable String id) {
        Project project = projectService.getProjectById(id).orElseThrow();
        ProjectEstimationRequest request = new ProjectEstimationRequest();
        request.setDescription(project.getDescription());
        request.setStartDate(project.getStartDate());
        request.setEndDate(project.getEndDate());
        request.setCategory(project.getCategory());
        return estimationService.estimateProject(request);
    }

    @GetMapping("/getProjectsNotInBoard")
    public List<Project> getProjectsNotInBoard() {
        return projectService.getProjectsNotInBoard();
    }





    }