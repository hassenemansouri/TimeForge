package tn.esprit.workspace_workflow.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.workspace_workflow.entity.Workspace;
import tn.esprit.workspace_workflow.repository.WorkspaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkspaceService implements IWorkspace{

    private WorkspaceRepository workspaceRepository;


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
}
