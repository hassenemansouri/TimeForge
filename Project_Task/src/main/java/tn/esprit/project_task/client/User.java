package tn.esprit.project_task.client;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import tn.esprit.project_task.entity.Project;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {
    private String name;
    private String email;
    private Role role;
    @DBRef
    private List<Project> projects = new ArrayList<>();

    public boolean isManager() {
        return this.role == Role.MANAGER;
    }
    public boolean isEmployee() {
        return this.role == Role.EMPLOYEE;
    }

}
