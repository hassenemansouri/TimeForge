package tn.esprit.project_task.entity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tasks")
public class Task {


    @Id
    private String id_task;

    @NotNull(message = "Task name cannot be null")
    @Size(min = 3, max = 100, message = "Task name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Project description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    private LocalDateTime createdAt = LocalDateTime.now();

    @FutureOrPresent(message = "Due date must be in the present or future")
    private LocalDateTime dueDate;
    private TaskPriority priority = TaskPriority.HIGH;

    private TaskStatus status = TaskStatus.TODO;

    @DBRef
    private Project project;

}
