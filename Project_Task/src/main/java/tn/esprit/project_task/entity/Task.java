package tn.esprit.project_task.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tasks")
public class Task {
    private String id_task;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
}
