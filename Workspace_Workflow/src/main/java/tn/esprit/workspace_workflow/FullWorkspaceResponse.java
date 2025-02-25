package tn.esprit.workspace_workflow;

import lombok.*;
import tn.esprit.workspace_workflow.client.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullWorkspaceResponse {

    private String Workspace_name;
    private String Workspace_description;
    private String Workflows;
    private List<User> users;
}
