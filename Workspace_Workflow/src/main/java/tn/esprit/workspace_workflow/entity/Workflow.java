package tn.esprit.workspace_workflow.entity;

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

    private String workflowName;

    private List<String> steps = new ArrayList<>();

    @DBRef
    private List<User> collaborators = new ArrayList<>();

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
