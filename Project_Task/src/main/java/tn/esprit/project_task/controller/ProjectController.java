package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.FullProjectResponse;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.service.ProjectImpl;

import java.time.LocalDate;
import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;

@RestController
@RequestMapping("/projects")
//http://localhost:8400/timeforge/swagger-ui/index.html#/
@AllArgsConstructor
public class ProjectController {

    private ProjectImpl service;

    @PostMapping("/add")
    public Project addProject(Project p) {

        Project project = service.addProject(p);
        return project;
    }

    @GetMapping()
    public List<Project> getAllProjects() {
        List<Project> listProjects =  service.findAllProjects ();
        return listProjects;
    }
    @DeleteMapping("/remove-project/{project-id}")
    public void removeProject(@PathVariable("project-id") String projet_id) {

        service.deleteProject(projet_id);
    }
    @PutMapping("/modify-project")
    public Project modifyProject(@RequestBody Project project) {
        Project p = service.modifyProject(project);
        return p;
    }



}