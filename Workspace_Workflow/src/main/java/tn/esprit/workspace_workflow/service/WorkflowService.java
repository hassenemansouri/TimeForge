package tn.esprit.workspace_workflow.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.workspace_workflow.entity.Workflow;
import tn.esprit.workspace_workflow.repository.WorkflowRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    // Créer un nouveau workflow
    public Workflow createWorkflow(Workflow workflow) {
        if (workflow == null) {
            throw new IllegalArgumentException("Le workflow ne peut pas être null.");
        }

        if (workflow.getWorkflowName() == null || workflow.getWorkflowName().isEmpty()) {
            throw new IllegalArgumentException("Le nom du workflow est obligatoire.");
        }

        if (workflow.getSteps() == null || workflow.getSteps().isEmpty()) {
            throw new IllegalArgumentException("Le workflow doit contenir au moins une étape.");
        }

        if (workflow.getCreator() == null) {
            throw new IllegalArgumentException("Le créateur du workflow est obligatoire.");
        }

        workflow.setId ( workflow.getId () );
        workflow.setWorkflowName ( workflow.getWorkflowName().trim() );
        workflow.setSteps ( workflow.getSteps() );
        workflow.setCreator ( workflow.getCreator() );
        workflow.setCollaborators ( workflow.getCollaborators() );

        System.out.println ("Workflow Created : " + workflow);

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
    public Workflow updateWorkflow(String workflowId, Workflow workflow) {
        Optional<Workflow> existingWorkflow = workflowRepository.findById(workflowId);
        if (existingWorkflow.isPresent()) {
                    workflow.setId ( workflowId );
                    workflow.setWorkflowName ( workflow.getWorkflowName() );
                    workflow.setSteps(workflow.getSteps());
                    workflow.setCollaborators (workflow.getCollaborators());
                    workflow.setCreator (workflow.getCreator());
                    log.info("Workflow mise à jour : {}", workflowId);
                }
        else {
            throw new RuntimeException("Workflow non trouvée : " + workflowId);
        }
        return workflowRepository.save(workflow);

    }

    // Supprimer un workflow
    public void deleteWorkflow(String workflowId) {
        if (workflowRepository.existsById(workflowId)) {
            workflowRepository.deleteById(workflowId);
            log.info("Workflow est supprime : {}", workflowId);
        } else {
            throw new RuntimeException("Workflow introuvable");
        }
    }


}
