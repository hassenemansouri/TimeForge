package tn.esprit.workspace_workflow.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.workspace_workflow.FullWorkspaceResponse;
import tn.esprit.workspace_workflow.client.UserClient;
import tn.esprit.workspace_workflow.entity.Workspace;
import tn.esprit.workspace_workflow.repository.WorkspaceRepository;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkspaceService {

    private WorkspaceRepository workspaceRepository;
    private UserClient userClient;


    public Workspace createWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }


    public Optional<Workspace> getWorkspaceById(String workspaceId) {
        return workspaceRepository.findById(workspaceId);
    }


    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }


    public Workspace updateWorkspace(String workspaceId, Workspace updatedWorkspace) {
        return workspaceRepository.findById(workspaceId).map(existingWorkspace -> {
            if (updatedWorkspace.getWorkspace_name() != null) {
                existingWorkspace.setWorkspace_name(updatedWorkspace.getWorkspace_name());
            }
            if (updatedWorkspace.getWorkspace_description() != null) {
                existingWorkspace.setWorkspace_description(updatedWorkspace.getWorkspace_description());
            }
            if (updatedWorkspace.getWorkflows() != null) {
                existingWorkspace.setWorkflows(updatedWorkspace.getWorkflows());
            }
            return workspaceRepository.save(existingWorkspace);


        }).orElseThrow(() -> new RuntimeException("Espace de travail introuvable : " + workspaceId));
    }


    public void deleteWorkspace(String workspaceId) {
        if (!workspaceRepository.existsById(workspaceId)) {
            throw new RuntimeException("Espace de travail introuvable");
        }
        workspaceRepository.deleteById(workspaceId);
    }


    public FullWorkspaceResponse findWorkspaceWithUsers(String workspaceId) {
        var workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new RuntimeException("Workspace introuvable : " + workspaceId));

        var users = userClient.fundAllUsersByWorkspace(workspaceId);

        return FullWorkspaceResponse.builder()
                .Workspace_name(workspace.getWorkspace_name())
                .Workspace_description(workspace.getWorkspace_description())
                .Workflows(workspace.getWorkflows())
                .users(users)
                .build();
    }

}
