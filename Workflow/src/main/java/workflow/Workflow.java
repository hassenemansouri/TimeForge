package workflow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    private List<String> steps;

    @DBRef
    private User creator;

    @DBRef
    private List<User> collaborators;

    @DBRef
    private Workspace workspace;
    public boolean inviteUser(User user) {
        if (this.creator.isManager()) {
            if (!collaborators.contains(user) && (user.isManager() || user.isEmployee())) {
                this.collaborators.add(user);
                return true;
            }
        }
        return false;
    }

}