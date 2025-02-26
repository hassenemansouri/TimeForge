package tn.esprit.workspace_workflow.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.workspace_workflow.entity.Workflow;
import tn.esprit.workspace_workflow.service.WorkflowService;
import tn.esprit.workspace_workflow.client.User;
import java.util.List;

@RestController
@RequestMapping("/workflows")
@AllArgsConstructor
public class WorkflowController {

    private final WorkflowService workflowService;


    @PostMapping("/create")
    public ResponseEntity<Workflow> createWorkflow(@RequestBody Workflow workflow) {
        try {
            Workflow createdWorkflow = workflowService.createWorkflow(workflow);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkflow);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/getWorkflowById/{workflowId}")
    public ResponseEntity<Workflow> getWorkflowById(@PathVariable String workflowId) {
        return workflowService.getWorkflowById(workflowId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllWorkflows")
    public ResponseEntity<List<Workflow>> getAllWorkflows() {
        return ResponseEntity.ok(workflowService.getAllWorkflows());
    }


    @PutMapping("/update/{workflowId}")
    public ResponseEntity<?> updateWorkflow(@PathVariable String workflowId,
                                            @RequestBody Workflow workflow) {
        try {
            Workflow updatedWorkflow = workflowService.updateWorkflow(workflowId, workflow);
            return ResponseEntity.ok(updatedWorkflow);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Workflow introuvable: " + e.getMessage());
        }
    }


    @DeleteMapping("/delete/{workflowId}")
    public ResponseEntity<Void> deleteWorkflow(@PathVariable String workflowId) {
        try {
            workflowService.deleteWorkflow(workflowId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/assign-collaborators/{workflowId}")
    public ResponseEntity<Workflow> assignCollaborators(@PathVariable String workflowId, @RequestBody List<User> collaborators) {
        Workflow updatedWorkflow = workflowService.assignCollaborators(workflowId, collaborators);
        return ResponseEntity.ok(updatedWorkflow);
    }

    @GetMapping("/Track/{workflowId}")
    public ResponseEntity<Double> trackWorkflow(@PathVariable String workflowId) {
        double progress = workflowService.trackWorkflow(workflowId);
        return ResponseEntity.ok(progress);
    }

}
