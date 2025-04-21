package tn.esprit.user_strategicpartership.entity;

import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@CompoundIndex(name = "search_idx", def = "{'name': 1, 'email': 1}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    private String id;
    private String photoBase64;  // Stores Base64-encoded image
    private String photoContentType;
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
    private String provider;
    private String providerId;
    private boolean isManager() {
        return this.role == Role.MANAGER;
    }
    private boolean isEmployee() {
        return this.role == Role.EMPLOYEE;
    }
    private boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}
