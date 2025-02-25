package tn.esprit.workspace_workflow.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.workspace_workflow.FullWorkspaceResponse;
import tn.esprit.workspace_workflow.client.UserClient;
import tn.esprit.workspace_workflow.entity.Workspace;
import tn.esprit.workspace_workflow.repository.WorkspaceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkspaceService {

    private WorkspaceRepository workspaceRepository;
    private UserClient userClient;


    /**
     * Créer un nouvel espace de travail avec contrôle de saisie
     */
    public Workspace createWorkspace( Workspace workspace) {
        if (workspace == null) {
            throw new IllegalArgumentException("Le workspace ne peut pas être null");
        }

        return workspaceRepository.save(workspace);
    }

    /**
     * Récupérer un espace de travail par ID
     */
    public Optional<Workspace> getWorkspaceById(String workspaceId) {
        return workspaceRepository.findById(workspaceId);
    }

    /**
     * Récupérer tous les espaces de travail
     */
    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    /**
     * Mettre à jour un espace de travail
     */
    public Workspace updateWorkspace(String workspaceId, String newName, String newDescription) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du workspace ne peut pas être vide");
        }

        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("La description du workspace ne peut pas être vide");
        }

        return workspaceRepository.findById(workspaceId)
                .map(workspace -> {
                    workspace.setWorkspace_name(newName);
                    workspace.setWorkspace_description(newDescription);
                    workspace.setWorkflows(new ArrayList<>());
                    return workspaceRepository.save(workspace);
                })
                .orElseThrow(() -> new RuntimeException("Espace de travail introuvable"));
    }

    /**
     * Supprimer un espace de travail
     */
    public void deleteWorkspace(String workspaceId) {
        if (!workspaceRepository.existsById(workspaceId)) {
            throw new RuntimeException("Espace de travail introuvable");
        }
        workspaceRepository.deleteById(workspaceId);
    }

    /**
     * Récupérer un espace de travail avec ses utilisateurs
     */
    public FullWorkspaceResponse findWorkspaceWithUsers(String workspaceId) {
        var workspace = workspaceRepository.findById(workspaceId)
                .orElse(Workspace.builder()
                        .Workspace_name("NOT_FOUND")
                        .Workspace_description("NOT-FOUND")
                        .Workflows(new ArrayList<>())
                        .build());

        var users = userClient.fundAllUsersByWorkspace(workspaceId);

        return FullWorkspaceResponse.builder()
                .Workspace_name(workspace.getWorkspace_name())
                .Workspace_description(workspace.getWorkspace_description())
                .Workflows(workspace.getWorkflows())
                .users(users)
                .build();
    }
}
