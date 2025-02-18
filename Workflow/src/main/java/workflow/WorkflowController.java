package workflow;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workflows")
@RequiredArgsConstructor
public class WorkflowController {
    private final WorkflowService workflowService;


    @PostMapping("/create")
    public ResponseEntity<Workflow> createWorkflow(@RequestBody WorkflowRequestDTO request) {
        Workflow workflow = workflowService.createWorkflow(
                request.getCreatorId(),
                request.getWorkflowName(),
                request.getSteps()
        );
        return ResponseEntity.ok(workflow);
    }


    @PostMapping("/{workflowId}/invite/{userId}")
    public ResponseEntity<String> inviteUser(@PathVariable String workflowId, @PathVariable String userId) {
        return workflowService.inviteUserToWorkflow(workflowId, userId)
                ? ResponseEntity.ok("Utilisateur invité avec succès")
                : ResponseEntity.badRequest().body("Invitation refusée");
    }


    @PutMapping("/update/{workflowId}")
    public ResponseEntity<Workflow> updateWorkflow(@PathVariable String workflowId, @RequestBody WorkflowRequestDTO request) {
        Workflow updatedWorkflow = workflowService.updateWorkflow(
                workflowId,
                request.getWorkflowName(),
                request.getSteps(),
                request.getCreatorId()
        );
        return ResponseEntity.ok(updatedWorkflow);
    }


    @DeleteMapping("/delete/{workflowId}/{creatorId}")
    public ResponseEntity<String> deleteWorkflow(@PathVariable String workflowId, @PathVariable String creatorId) {
        workflowService.deleteWorkflow(workflowId, creatorId);
        return ResponseEntity.ok("Workflow supprimé avec succès");
    }


    @GetMapping("/all")
    public ResponseEntity<List<Workflow>> getAllWorkflows() {
        return ResponseEntity.ok(workflowService.getAllWorkflows());
    }


    @GetMapping("/{workflowId}")
    public ResponseEntity<Workflow> getWorkflowById(@PathVariable String workflowId) {
        return ResponseEntity.of(workflowService.getWorkflowById(workflowId));
    }
}