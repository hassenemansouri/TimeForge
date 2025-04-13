package tn.esprit.project_task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Document(collection = "projects")
public class Project {

    @Id
    @Column(name = "project_id")
    private String projet_id;
    @NotNull(message = "Project title cannot be null")
    @Size(min = 3, max = 50, message = "Project title must be between 3 and 50 characters")
    private String title;

    @NotNull(message = "Project description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Due date must be in the present or future")
    private Date endDate;

    private ProjectCategory category = ProjectCategory.DESIGN;

    @DBRef
    private User owner;
    @DBRef
    private List<User> members = new ArrayList<>();

    public boolean inviteUser(User user) {
        if (this.owner != null && this.owner.isManager()) {
            if (user != null && !this.members.contains(user) && (user.isManager() || user.isEmployee())) {
                this.members.add(user);
                return true;
            }
        }
        return false;
    }


}