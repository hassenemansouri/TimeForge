package tn.esprit.workspace_workflow.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    private String uuid;
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String calendarId;  // ID du calendrier auquel ce rendez-vous appartient



    // Getters et Setters
}