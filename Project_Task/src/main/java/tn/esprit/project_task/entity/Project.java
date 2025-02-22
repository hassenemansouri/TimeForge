package tn.esprit.project_task.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "projects")
public class Project {
    @Id
    private String projet_id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    // private ProjectStatus status;
    //@DBRef
    //private List<Task> tasks;
}