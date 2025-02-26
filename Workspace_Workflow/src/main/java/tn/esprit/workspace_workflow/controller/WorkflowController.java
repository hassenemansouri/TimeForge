package tn.esprit.workspace_workflow.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> createWorkflow(@RequestParam("workflowName") @NonNull String workflowName ,
                                            @RequestParam("steps") @NonNull String steps,
                                            @RequestParam("creator") @NonNull String creator) {
        try {
            Workflow workflow = new Workflow ();

            workflowService.createWorkflow(workflow);

            System.out.println ("Workflow Name : " + workflowName
                    + " Steps :" + steps +
                    " Creator : " + creator);

            return ResponseEntity.status( HttpStatus.CREATED).body("Workflow added successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
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


    @PutMapping("update/{workflowId}")
    public ResponseEntity<String> updateWorkflow(@RequestParam("workflowName") @NonNull String workflowName ,
                                                 @RequestParam("steps") @NonNull String steps,
                                                 @RequestParam("creator") @NonNull String creator,
                                                  @PathVariable String workflowId) {

        try {
            Workflow workflow = new Workflow ();

            workflowService.updateWorkflow ( workflowId, workflow );

            System.out.println ("Workflow Name : " + workflowName
                    + " Steps :" + steps +
                    " Creator : " + creator);

            return ResponseEntity.status(HttpStatus.OK).body("Workflow updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWorkflow(@PathVariable String id) {
        workflowService.deleteWorkflow(id);
        return ResponseEntity.noContent().build();
    }
}
