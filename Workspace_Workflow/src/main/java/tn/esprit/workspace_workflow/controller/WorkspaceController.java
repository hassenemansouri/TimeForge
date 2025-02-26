package tn.esprit.workspace_workflow.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final WorkspaceService workspaceService;


    @PostMapping("/create")
    public ResponseEntity<Workspace> createWorkspace(@RequestBody Workspace workspace) {
        try {
            Workspace createdWorkspace = workspaceService.createWorkspace(workspace);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkspace);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping("/getWorkspaceById/{workspaceId}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable String workspaceId) {
        return workspaceService.getWorkspaceById(workspaceId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Récupérer tous les workspaces
     */
    @GetMapping("/getAllWorkspaces")
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        return ResponseEntity.ok(workspaceService.getAllWorkspaces());
    }


    @PutMapping("/update/{workspaceId}")
    public ResponseEntity<?> updateWorkspace(@PathVariable String workspaceId,
                                             @RequestBody Workspace workspace) {
        try {
            Workspace updatedWorkspace = workspaceService.updateWorkspace(workspaceId, workspace);
            return ResponseEntity.ok(updatedWorkspace);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Espace de travail introuvable: " + e.getMessage());
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


    @GetMapping("/withUsers/{workspaceId}")
    public ResponseEntity<FullWorkspaceResponse> findWorkspaceWithUsers(@PathVariable String workspaceId) {
        try {
            return ResponseEntity.ok(workspaceService.findWorkspaceWithUsers(workspaceId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
