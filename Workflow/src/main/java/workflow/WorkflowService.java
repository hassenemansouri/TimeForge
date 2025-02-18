package workflow;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkflowService {
    private final WorkflowRepository workflowRepository;
    private final UserRepository userRepository;


    public Workflow createWorkflow(String creatorId, String workflowName, List<String> steps) {
        Optional<User> creator = userRepository.findById(creatorId);
        if (creator.isPresent() && creator.get().isManager()) {
            Workflow workflow = new Workflow();
            workflow.setWorkflowName(workflowName);
            workflow.setCreator(creator.get());
            workflow.setSteps(steps);
            return workflowRepository.save(workflow);
        }
        throw new RuntimeException("Seuls les managers peuvent créer un workflow");
    }


    public boolean inviteUserToWorkflow(String workflowId, String userId) {
        Optional<Workflow> workflow = workflowRepository.findById(workflowId);
        Optional<User> user = userRepository.findById(userId);

        if (workflow.isPresent() && user.isPresent()) {
            boolean invited = workflow.get().inviteUser(user.get());
            if (invited) {
                workflowRepository.save(workflow.get());
            }
            return invited;
        }
        throw new RuntimeException("Workflow ou utilisateur introuvable");
    }


    public Workflow updateWorkflow(String workflowId, String newName, List<String> newSteps, String creatorId) {
        Optional<Workflow> workflow = workflowRepository.findById(workflowId);
        if (workflow.isPresent() && workflow.get().getCreator().getId().equals(creatorId)) {
            workflow.get().setWorkflowName(newName);
            workflow.get().setSteps(newSteps);
            return workflowRepository.save(workflow.get());
        }
        throw new RuntimeException("Accès refusé ou workflow introuvable");
    }


    public void deleteWorkflow(String workflowId, String creatorId) {
        Optional<Workflow> workflow = workflowRepository.findById(workflowId);
        if (workflow.isPresent() && workflow.get().getCreator().getId().equals(creatorId)) {
            workflowRepository.delete(workflow.get());
        } else {
            throw new RuntimeException("Accès refusé ou workflow introuvable");
        }
    }


    public List<Workflow> getAllWorkflows() {
        return workflowRepository.findAll();
    }


    public Optional<Workflow> getWorkflowById(String workflowId) {
        return workflowRepository.findById(workflowId);
    }
}