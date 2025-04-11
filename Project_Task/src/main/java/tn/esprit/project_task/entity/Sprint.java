package tn.esprit.project_task.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "sprints")
public class Sprint {

    @Id
    @Column(name = "id_sprint")
    private String id_sprint;

    @NotNull(message = "Sprint name cannot be null")
    @Size(min = 3, max = 100, message = "Sprint name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Sprint description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date begin = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "End must be in the present or future")
    private Date end;
}