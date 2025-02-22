package tn.esprit.workspace_workflow.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "workspaces")
public class Workspace {
    @Id
    private String id;
    private String Workspace_name;

    //@DBRef
   // List<Workflow> Workflows;


}
