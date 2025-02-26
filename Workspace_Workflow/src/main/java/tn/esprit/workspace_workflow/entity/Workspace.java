package tn.esprit.workspace_workflow.entity;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Workspace_name cannot be null")
    private String Workspace_name;

    @NotNull(message = "Workspace_description cannot be null")
    private String Workspace_description;

    @DBRef
    private List<Workflow> Workflows;



}
