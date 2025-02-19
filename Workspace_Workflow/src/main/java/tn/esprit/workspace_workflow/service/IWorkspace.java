package tn.esprit.workspace_workflow.service;

import tn.esprit.workspace_workflow.entity.Workspace;
import java.util.List;

public interface IWorkspace {
    Workspace createWorkSpace(String managerId, String Workspace_name);
    List<Workspace> getWorkspacesByManager(String managerId);
    List<Workspace> getAllWorkspaces();
    Workspace updateWorkSpace(String workspaceId, String newName, String managerId);
    void deleteWorkSpace(String workspaceId, String managerId);

}
