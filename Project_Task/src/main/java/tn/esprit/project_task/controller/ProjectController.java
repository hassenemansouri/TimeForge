package tn.esprit.project_task.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project_task.FullProjectResponse;
import tn.esprit.project_task.entity.Project;
import tn.esprit.project_task.service.ProjectImpl;

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
    @GetMapping("WithUsers/{projet-id}")
    public ResponseEntity<FullProjectResponse> findCollaborations(@PathVariable("projet-id") String projet_id){
        return ResponseEntity.ok (service.findProjectsWithUsers(projet_id));

    }

    // http://localhost:8089/tpfoyer/chambre/retrieve-chambre/8
    //@GetMapping("/retrieve-chambre/{chambre-id}")
   // public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
     //   Chambre chambre = chambreService.retrieveChambre(chId);
     //   return chambre;
    //}

    @DeleteMapping("/remove-project/{project-id}")
    public void removeProject(@PathVariable("project-id") String projet_id) {

        service.deleteProject(projet_id);
    }
    @PutMapping("/modify-project")
    public Project modifyProject(@RequestBody Project project) {
        Project chambre = service.modifyProject(project);
        return chambre;
    }
}