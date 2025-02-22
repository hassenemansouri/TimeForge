package tn.esprit.user_strategicpartership.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Integer id;
    private String name;
    private String email;
    private Role role;
    private Integer workspaceId;


    //@DBRef
    //private List<Workspace> workspaces;

    public boolean isManager() {
        return this.role == Role.MANAGER;
    }
    public boolean isEmployee() {
        return this.role == Role.EMPLOYEE;
    }
}
