package tn.esprit.workspace;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/workspaces")
public class WorkspaceController {

    private WorkspaceService workspaceService;

    @PostMapping
    public Workspace createWorkspace(@RequestParam String managerId, @RequestParam String Workspace_name) {
        return workspaceService.createWorkSpace ( managerId, Workspace_name );
    }
    @GetMapping
    public List<Workspace> getAllWorkspaces() {
        return workspaceService.getAllWorkspaces();
    }
    @GetMapping
    public List<Workspace> getWorkspacesByManager (@RequestParam String managerId) {
        return  workspaceService.getWorkspacesByManager ( managerId );
    }
    @PutMapping("/{id}")
    public Workspace updateWorkSpace(@PathVariable String id, @RequestParam String newName, @RequestParam String managerId) {
        return workspaceService.updateWorkSpace(id, newName, managerId);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkSpace(@PathVariable String id, @RequestParam String managerId) {
        workspaceService.deleteWorkSpace(id, managerId);
    }
}