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
public class WorkspaceService implements IWorkspace{

    private WorkspaceRepository workspaceRepository;

    private UserClient userClient;

    public Workspace createWorkSpace(Workspace workspace) {
        return workspaceRepository.save(workspace);

    }

    public List<Workspace> getWorkspacesByManager(String managerId) {
        return List.of ();
    }

    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    public Workspace updateWorkSpace(Workspace workspace) {
        return workspaceRepository.save (workspace);
    }

    public void deleteWorkSpace(Workspace workspace) {
        workspaceRepository.delete (workspace);

    }

    public FullWorkspaceResponse findWorkspaceWithUsers(String workspaceId) {
        var workspace = workspaceRepository.findById(workspaceId)
                .orElse(Workspace.builder()
                        .Workspace_name("NOT_FOUND") // Correction du nom du champ
                        .build());
        var users = userClient.fundAllUsersByWorkspace ( workspaceId);
        return FullWorkspaceResponse.builder ()
                .Workspace_name ( workspace.getWorkspace_name () )
                .Workspace_description ( workspace.getWorkspace_description () )
                .users ( users )
                .build ();
    }
}
