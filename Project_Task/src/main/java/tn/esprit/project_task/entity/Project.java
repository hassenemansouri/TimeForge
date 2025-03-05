package tn.esprit.project_task.entity;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.project_task.client.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "projects")
public class Project {

    @Id
    private String projet_id;
    @NotNull(message = "Project title cannot be null")
    @Size(min = 3, max = 50, message = "Project title must be between 3 and 50 characters")
    private String title;
    @NotNull(message = "Project description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;
    private LocalDateTime startDate = LocalDateTime.now();

    @FutureOrPresent(message = "Due date must be in the present or future")
    private LocalDateTime endDate;

    private ProjectStatus status = ProjectStatus.IN_PROGRESS;
    @DBRef
    private User owner;
    @DBRef
    private List<Task> tasks;
   // public boolean inviteUser(User user) {
        //if (this.creator != null && this.creator.isManager()) {
      //      if (user != null && !collaborators.contains(user) && (user.isManager() || user.isEmployee())) {
    //            collaborators.add(user);
      //          return true;
      //      }
      //  }
     //   return false;
   //}

}