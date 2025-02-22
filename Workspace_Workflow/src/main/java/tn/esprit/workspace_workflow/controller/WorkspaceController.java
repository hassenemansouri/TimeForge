package tn.esprit.workspace_workflow.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.workspace_workflow.entity.Workspace;
import tn.esprit.workspace_workflow.service.WorkspaceService;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
@AllArgsConstructor
public class WorkspaceController {

    private WorkspaceService service;

    @PostMapping("/create")
    public Workspace createWorkSpace(Workspace workspace) {
        return service.createWorkSpace (workspace);
    }
    @GetMapping
    public List<Workspace> getAllWorkspaces() {
        return service.getAllWorkspaces();
    }
    @PutMapping
    public Workspace updateWorkSpace(Workspace workspace){
        return service.updateWorkSpace(workspace);
    }
    @DeleteMapping
    public void deleteWorkSpace(Workspace workspace){
        service.deleteWorkSpace(workspace);
    }
}
