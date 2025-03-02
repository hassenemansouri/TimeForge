package tn.esprit.user_strategicpartership.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    private String id;  // Correction : MongoDB utilise souvent un String pour l'ID
    private String name;
    private String email;
    private String workspaceId;
    private String collaborationId;
    private String goalId;
    private String projectId;
    private Role role;

    private boolean isManager() {
        return this.role == Role.MANAGER;
    }
    private boolean isEmployee() {
        return this.role == Role.EMPLOYEE;
    }
}
