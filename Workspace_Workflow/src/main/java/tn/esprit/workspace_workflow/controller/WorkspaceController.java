package tn.esprit.workspace_workflow.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.workspace_workflow.entity.Workspace;
import tn.esprit.workspace_workflow.service.WorkspaceService;

@RestController
@RequestMapping("/workspaces")
@AllArgsConstructor
public class WorkspaceController {

    private WorkspaceService service;

    @PostMapping("/create")
    public Workspace createWorkSpace(Workspace workspace) {
        return service.createWorkSpace (workspace);
    }
}
