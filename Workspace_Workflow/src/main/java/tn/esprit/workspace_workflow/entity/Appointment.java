package tn.esprit.workspace_workflow.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    private String id;

    private String title;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private String color;

    // Getters & Setters
}
