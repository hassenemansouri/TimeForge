package workflow;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkflowRequestDTO {
    private String creatorId;
    private String workflowName;
    private List<String> steps;
}