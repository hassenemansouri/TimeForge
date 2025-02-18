package tn.esprit.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private Role role;

    @DBRef
    private List<Workspace> workspaces;

    public boolean isManager() {
        return this.role == Role.MANAGER;
    }
    public boolean isEmployee() {
        return this.role == Role.EMPLOYEE;
    }
}