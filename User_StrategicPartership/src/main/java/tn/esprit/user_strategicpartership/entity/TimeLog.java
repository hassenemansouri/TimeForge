package tn.esprit.user_strategicpartership.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDateTime;

@Document(collection = "time_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeLog {
  @Id
  private String id;

  @DBRef
  private User user;

  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private Long durationMinutes; // Calculated field

  private String activityDescription;
  private String projectId; // To match your User entity field
  private String taskId; // Optional for more granular tracking

  private boolean IsActive; // For tracking currently running timers
  private LocalDateTime createdAt;

  // Automatically calculate duration when endTime is set
  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
    if (this.startTime != null) {
      this.durationMinutes = java.time.Duration.between(startTime, endTime).toMinutes();
    }
  }
}