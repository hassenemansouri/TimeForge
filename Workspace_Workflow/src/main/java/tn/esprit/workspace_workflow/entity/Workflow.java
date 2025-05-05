package tn.esprit.workspace_workflow.entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "workflows")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workflow {
    @Id
    private String id = UUID.randomUUID().toString();

    @NotNull(message = "workflowName cannot be null")
    private String workflowName;

    @NotNull(message = "Steps cannot be null")
    private List<String> steps = new ArrayList<>();

    @NotNull(message = "FileName cannot be null")
    private String fileName;

    @NotNull(message = "startDate cannot be null")
    private Date startDate;

    @NotNull(message = "endDate cannot be null")
    private Date endDate;



}
