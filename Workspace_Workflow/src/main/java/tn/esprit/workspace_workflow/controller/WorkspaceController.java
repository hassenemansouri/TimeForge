package tn.esprit.workspace_workflow.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.workspace_workflow.FullWorkspaceResponse;
import tn.esprit.workspace_workflow.entity.Workspace;
import tn.esprit.workspace_workflow.service.WorkspaceService;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
@AllArgsConstructor
//http://localhost:8500/timeforge/swagger-ui/index.html#
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
    @GetMapping("WithUsers/{workspace-id}")
    public ResponseEntity<FullWorkspaceResponse> findWorkspaces(@PathVariable("workspace-id") String workspaceId){
        return ResponseEntity.ok (service.findWorkspaceWithUsers(workspaceId));

    }
}
