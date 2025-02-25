package tn.esprit.workspace_workflow.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.workspace_workflow.client.User;
import tn.esprit.workspace_workflow.entity.Workflow;
import tn.esprit.workspace_workflow.repository.WorkflowRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    // Créer un nouveau workflow
    public Workflow createWorkflow(Workflow workflow) {
        return workflowRepository.save(workflow);
    }

    // Obtenir un workflow par ID
    public Optional<Workflow> getWorkflowById(String workflowId) {
        return workflowRepository.findById(workflowId);
    }

    // Obtenir tous les workflows
    public List<Workflow> getAllWorkflows() {
        return workflowRepository.findAll();
    }

    // Mettre à jour un workflow
    public Workflow updateWorkflow(String workflowId, String newName, List<String> newSteps , List<User> newUsers , User newCreator) {
        return workflowRepository.findById(workflowId)
                .map(workflow -> {
                    workflow.setId ( workflowId );
                    workflow.setWorkflowName ( newName );
                    workflow.setSteps(newSteps);
                    workflow.setCollaborators ( newUsers );
                    workflow.setCreator (newCreator);

                    return workflowRepository.save(workflow);
                })
                .orElseThrow(() -> new RuntimeException("Workflow introuvable"));
    }

    // Supprimer un workflow
    public void deleteWorkflow(String workflowId) {
        if (workflowRepository.existsById(workflowId)) {
            workflowRepository.deleteById(workflowId);
        } else {
            throw new RuntimeException("Workflow introuvable");
        }
    }


}
