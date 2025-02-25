package tn.esprit.workspace_workflow.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.workspace_workflow.FullWorkspaceResponse;
import tn.esprit.workspace_workflow.entity.Workspace;
import tn.esprit.workspace_workflow.service.WorkspaceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workspaces")
@AllArgsConstructor
public class WorkspaceController {

    private WorkspaceService workspaceService;


    @PostMapping("/create")
    public ResponseEntity<Workspace> createWorkspace(@RequestBody Workspace workspace) {
        Workspace createdWorkspace = workspaceService.createWorkspace(workspace);
        return ResponseEntity.ok(createdWorkspace);
    }


    @GetMapping("getWorkspaceById/{workspaceId}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable String workspaceId) {
        Optional<Workspace> workspace = workspaceService.getWorkspaceById(workspaceId);
        return workspace.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/getAllWorkspaces")
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceService.getAllWorkspaces();
        return ResponseEntity.ok(workspaces);
    }


    @PutMapping("update/{workspaceId}")
    public ResponseEntity<Workspace> updateWorkspace(
            @PathVariable String workspaceId) {
        try {
            Workspace updatedWorkspace = workspaceService.updateWorkspace(workspaceId);
            return ResponseEntity.ok(updatedWorkspace);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable String workspaceId) {
        try {
            workspaceService.deleteWorkspace(workspaceId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("WithUsers/{workspace-id}")
    public ResponseEntity<FullWorkspaceResponse> findWorkspaces(@PathVariable("workspace-id") String workspaceId){
        return ResponseEntity.ok (workspaceService.findWorkspaceWithUsers(workspaceId));

    }
}
