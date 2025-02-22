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
    private List<User> users;
}
