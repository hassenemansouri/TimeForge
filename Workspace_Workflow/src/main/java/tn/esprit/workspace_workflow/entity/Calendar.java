package tn.esprit.workspace_workflow.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "calendars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {

    @Id
    private String id;
    private LocalDate month;
    private List<Appointment> appointments;

}
