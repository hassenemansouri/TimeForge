package tn.esprit.workspace_workflow.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.user_strategicpartership.entity.User;

import java.util.List;

@Document(collection = "workflows")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workflow {

    @Id
    private String id;

    private String Workflow_name;

    private List<String> steps;

    @DBRef
    private List<User> collaborators;



}
