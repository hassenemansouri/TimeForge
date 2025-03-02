package tn.esprit.workspace_workflow.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
    @JsonProperty("workspaceName")
    private String workspaceName;

    @NotNull(message = "Workspace_description cannot be null")
    @JsonProperty("workspaceDescription")
    private String workspaceDescription;

    @DBRef
    private List<Workflow> Workflows;


    public Workspace(String workspaceName, String workspaceDescription) {
        this.workspaceName = workspaceName;
        this.workspaceDescription = workspaceDescription;
    }

}
