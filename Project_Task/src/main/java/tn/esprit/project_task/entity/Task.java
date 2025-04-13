package tn.esprit.project_task.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.project_task.client.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tasks")
public class Task {

    @Id
    @JsonProperty("_id")
    private String _id;

    @NotNull(message = "Task name cannot be null")
    @Size(min = 3, max = 100, message = "Task name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Task description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Due date must be in the present or future")
    private Date dueDate;
    private TaskPriority priority = TaskPriority.HIGH;
    private String columnId;
    private List<String> history;

    @DBRef
    private Project project;
    @DBRef
    private User assignedTo;
}
