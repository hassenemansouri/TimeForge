package tn.esprit.workspace_workflow.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class WorkspaceService {

    private WorkspaceRepository workspaceRepository;
    private UserClient userClient;


    public Workspace createWorkspace(Workspace workspace) {
        if (workspace == null) {
            throw new IllegalArgumentException("Le workspace ne peut pas être null.");
        }

        if (workspace.getWorkspace_name () == null || workspace.getWorkspace_name ().isEmpty()) {
            throw new IllegalArgumentException("Le nom du workspace est obligatoire.");
        }

        if (workspace.getWorkspace_description () == null || workspace.getWorkspace_description().isEmpty()) {
            throw new IllegalArgumentException("La description du workspace est obligatoire.");
        }

        return workspaceRepository.save(workspace);
    }



    public Optional<Workspace> getWorkspaceById(String workspaceId) {
        return workspaceRepository.findById(workspaceId);
    }

    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }


    public Workspace updateWorkspace(String workspaceId , Workspace workspace) {
        Optional<Workspace> existingWorkspace = workspaceRepository.findById(workspaceId);

        if (existingWorkspace.isPresent()) {
            workspace.setId(workspaceId);
            workspace.setWorkspace_name(workspace.getWorkspace_name());
            workspace.setWorkspace_description(workspace.getWorkspace_description());
            workspace.setWorkflows(workspace.getWorkflows());

            log.info("Espace de travail mis à jour : {}", workspaceId);

            return workspaceRepository.save(workspace);
        } else {
            throw new RuntimeException("Espace de travail introuvable");
        }
    }



    public void deleteWorkspace(String workspaceId) {
        if (!workspaceRepository.existsById(workspaceId)) {
            throw new RuntimeException("Espace de travail introuvable");
        }
        workspaceRepository.deleteById(workspaceId);
    }


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
