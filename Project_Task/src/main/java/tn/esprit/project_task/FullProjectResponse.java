package tn.esprit.project_task;

import lombok.*;
import tn.esprit.project_task.client.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullProjectResponse {
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<User> users;
}
