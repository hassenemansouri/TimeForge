package tn.esprit.workspace_workflow.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
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

    private WorkspaceService workspaceService;


    @PostMapping("/create")
    public ResponseEntity<String> createWorkspace(@RequestParam("Workspace_name") @NonNull String Workspace_name ,
                                                  @RequestParam("Workspace_description") @NonNull String Workspace_description
    ) {

        try {
            Workspace workSpace = new Workspace ();
            workspaceService.createWorkspace ( workSpace );

            System.out.println ("Workspace name : " + Workspace_name +
                    " Description : " + Workspace_description);

            return ResponseEntity.status( HttpStatus.CREATED).body("Workspace added successfully");

        }
        catch (Exception e) {
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                    .body("An error occurred: " + e.getMessage());
        }

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
    public ResponseEntity<String> updateWorkspace(@RequestParam("Workspace_name") @NonNull String workspaceName,
                                                  @RequestParam("Workspace_description") @NonNull String workspaceDescription,
                                                  @PathVariable String workspaceId) {

        try {
            Workspace workspace = new Workspace();

            workspaceService.updateWorkspace(workspace, workspaceId);

            System.out.println("Workspace name: " + workspaceName +
                    " Description: " + workspaceDescription);

            return ResponseEntity.status(HttpStatus.OK).body("Workspace updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
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
