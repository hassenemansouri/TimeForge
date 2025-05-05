package tn.esprit.project_task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.project_task.client.User;

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
    @JsonProperty("project_id")
    private String project_id;
    @NotNull
    @Size(min = 3, max = 50)
    private String title;

    @NotNull
    @Size(min = 10)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Due date must be in the present or future")
    private Date endDate;

    private ProjectCategory category = ProjectCategory.DESIGN;

    @DBRef
    private List<Task> tasks;


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