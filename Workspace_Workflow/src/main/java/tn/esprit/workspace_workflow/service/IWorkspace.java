package tn.esprit.workspace_workflow.service;

import tn.esprit.workspace_workflow.entity.Workspace;
import java.util.List;

public interface IWorkspace {
    Workspace createWorkSpace(Workspace workspace);
    List<Workspace> getWorkspacesByManager(String managerId);
    List<Workspace> getAllWorkspaces();
    Workspace updateWorkSpace(Workspace workspace);
    void deleteWorkSpace(Workspace workspace);

}
