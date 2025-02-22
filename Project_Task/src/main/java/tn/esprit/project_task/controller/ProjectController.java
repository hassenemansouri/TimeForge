package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.FullProjectResponse;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.service.ProjectImpl;

import java.util.List;

@RestController
@RequestMapping("/api/collaborations")
//http://localhost:8300/timeforge/swagger-ui/index.html#/
@AllArgsConstructor
public class ProjectController {

    private ProjectImpl service;


    @PostMapping("/add")
    public void addProject(Project project) {
        service.addProject ( project );
    }

    @GetMapping()
    public List<Project> getAllProjects() {
        return service.findAllProjects ();

    }
    @GetMapping("WithUsers/{projet-id}")
    public ResponseEntity<FullProjectResponse> findCollaborations(@PathVariable("projet-id") String projet_id){
        return ResponseEntity.ok (service.findProjectsWithUsers(projet_id));

    }
}