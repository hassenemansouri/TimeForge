package tn.esprit.workspace_workflow.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.workspace_workflow.client.User;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "workflows")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workflow {
    @Id
    private String id;

    @NotNull(message = "workflowName cannot be null")
    private String workflowName;

    @NotNull(message = "Steps cannot be null")
    private List<String> steps = new ArrayList<>();

    @NotNull(message = "collaborators cannot be null")
    @DBRef
    private List<User> collaborators = new ArrayList<>();

    @NotNull(message = "creator cannot be null")
    @DBRef
    private User creator;

    public boolean inviteUser(User user) {
        if (this.creator != null && this.creator.isManager()) {
            if (user != null && !collaborators.contains(user) && (user.isManager() || user.isEmployee())) {
                collaborators.add(user);
                return true;
            }
        }
        return false;
    }
}
