package tn.esprit.workspace_workflow.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.workspace_workflow.entity.Workflow;
import tn.esprit.workspace_workflow.service.WorkflowService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workflows")
@AllArgsConstructor
public class WorkflowController {

    private WorkflowService workflowService;


    @PostMapping("/create")
    public ResponseEntity<Workflow> createWorkflow(@RequestBody Workflow workflow) {
        return ResponseEntity.ok(workflowService.createWorkflow(workflow));
    }


    @GetMapping("/getWorkflowById/{id}")
    public ResponseEntity<Workflow> getWorkflowById(@PathVariable String id) {
        Optional<Workflow> workflow = workflowService.getWorkflowById(id);
        return workflow.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/getAllWorkflows")
    public ResponseEntity<List<Workflow>> getAllWorkflows() {
        return ResponseEntity.ok(workflowService.getAllWorkflows());
    }


    @PutMapping("/update/{workflowId}")
    public ResponseEntity<Workflow> updateWorkflow(@PathVariable String workflowId , @RequestBody Workflow workflow) {
        return ResponseEntity.ok(workflowService.updateWorkflow(workflowId, workflow));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWorkflow(@PathVariable String id) {
        workflowService.deleteWorkflow(id);
        return ResponseEntity.noContent().build();
    }
}
