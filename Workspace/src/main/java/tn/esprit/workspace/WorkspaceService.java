package tn.esprit.workspace;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    public Workspace createWorkSpace(String managerId, String Workspace_name) {
        Optional<User> manager = userRepository.findById(managerId);
        if (manager.isPresent() && manager.get().isManager()) {
            Workspace workspace = new Workspace();
            workspace.setWorkspace_name (Workspace_name);
            workspace.setManager(manager.get());
            return workspaceRepository.save(workspace);
        } else {
            throw new RuntimeException("Seuls les managers peuvent créer un workspace");
        }
    }

    public List<Workspace> getWorkspacesByManager(String managerId) {
        return workspaceRepository.findByManagerId(managerId);
    }

    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }


    public Workspace updateWorkSpace(String workspaceId, String newName, String managerId) {
        Optional<Workspace> workspace = workspaceRepository.findById( workspaceId );
        if (workspace.isPresent() && workspace.get().getManager().getId().equals(managerId)) {
            workspace.get().setWorkspace_name (newName);
            return workspaceRepository.save(workspace.get());
        }
        throw new RuntimeException("Accès refusé ou workspace introuvable");
    }

    public void deleteWorkSpace(String workspaceId, String managerId) {
        Optional<Workspace> workspace = workspaceRepository.findById(workspaceId);
        if (workspace.isPresent() && workspace.get().getManager().getId().equals(managerId)) {
            workspaceRepository.delete(workspace.get());
        } else {
            throw new RuntimeException("Accès refusé ou workspace introuvable");
        }
    }

}