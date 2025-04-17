package tn.esprit.user_strategicpartership.entity;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    private String id;  // Correction : MongoDB utilise souvent un String pour l'ID
    private String name;
    private String email;
    private String password;
    private String workspaceId;
    private String collaborationId;
    private String goalId;
    private String projectId;
    private Role role;
    private String resetToken;
    private LocalDateTime resetTokenExpiration;
    private boolean isManager() {
        return this.role == Role.MANAGER;
    }
    private boolean isEmployee() {
        return this.role == Role.EMPLOYEE;
    }
}
