package tn.esprit.workspace_workflow.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.workspace_workflow.entity.Workspace;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkspaceService implements IWorkspace{
    public Workspace createWorkSpace(String managerId, String Workspace_name) {
        return null;
    }

    public List<Workspace> getWorkspacesByManager(String managerId) {
        return List.of ();
    }

    public List<Workspace> getAllWorkspaces() {
        return List.of ();
    }

    public Workspace updateWorkSpace(String workspaceId, String newName, String managerId) {
        return null;
    }

    public void deleteWorkSpace(String workspaceId, String managerId) {

    }
}
