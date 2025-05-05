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
import java.util.ArrayList;
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

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(min = 10)
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent()
    private Date dueDate;
    private TaskPriority priority = TaskPriority.HIGH;
    private String columnId;
    private List<String> history;

    @DBRef
    private Project project;
    @DBRef
    private User assignedTo;



}
