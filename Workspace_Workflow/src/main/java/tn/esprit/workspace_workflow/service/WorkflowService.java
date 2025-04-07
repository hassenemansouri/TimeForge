package tn.esprit.workspace_workflow.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.workspace_workflow.client.User;
import tn.esprit.workspace_workflow.entity.Workflow;
import tn.esprit.workspace_workflow.repository.WorkflowRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class WorkflowService {

    private WorkflowRepository workflowRepository;


    public Workflow createWorkflow(Workflow workflow) {
        if (workflow.getId() == null || workflow.getId().isEmpty()) {
            workflow.setId( UUID.randomUUID().toString());
        }
        return workflowRepository.save(workflow);
    }


    public Optional<Workflow> getWorkflowById(String workflowId) {
        return workflowRepository.findById(workflowId);
    }


    public List<Workflow> getAllWorkflows() {
        return workflowRepository.findAll();
    }


    public Workflow updateWorkflow(String workflowId, Workflow updatedWorkflow) {
        return workflowRepository.findById(workflowId)
                .map(existingWorkflow -> {
                    if (updatedWorkflow.getWorkflowName() != null) {
                        existingWorkflow.setWorkflowName(updatedWorkflow.getWorkflowName().trim());
                    }
                    if (updatedWorkflow.getSteps() != null) {
                        existingWorkflow.setSteps(updatedWorkflow.getSteps());
                    }
                    if (updatedWorkflow.getCollaborators() != null) {
                        existingWorkflow.setCollaborators(updatedWorkflow.getCollaborators());
                    }
                    if (updatedWorkflow.getCreator() != null) {
                        existingWorkflow.setCreator(updatedWorkflow.getCreator());
                    }

                    log.info("Workflow mis à jour : {}", existingWorkflow);
                    return workflowRepository.save(existingWorkflow);
                })
                .orElseThrow(() -> new RuntimeException("Workflow non trouvé : " + workflowId));
    }


    public void deleteWorkflow(String workflowId) {
        if (workflowRepository.existsById(workflowId)) {
            workflowRepository.deleteById(workflowId);
            log.info("Workflow supprimé : {}", workflowId);
        } else {
            throw new RuntimeException("Workflow introuvable : " + workflowId);
        }
    }

    public Workflow assignCollaborators(String workflowId, List<User> collaborators) {
        Workflow workflow = workflowRepository.findById(workflowId)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));

        if (collaborators == null || collaborators.isEmpty()) {
            throw new IllegalArgumentException("The list of collaborators cannot be empty.");
        }

        workflow.setCollaborators(collaborators);
        log.info("Collaborators assigned to workflow: {}", workflowId);
        return workflowRepository.save(workflow);
    }

    public String trackWorkflow(String workflowId) {

        boolean exists = workflowRepository.existsById(workflowId);
        return exists ? "Valide" : "Non Valide";
    }


}
