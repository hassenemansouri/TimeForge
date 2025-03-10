package tn.esprit.goal_reward.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.goal_reward.Entity.Reward;

import java.util.Date;
import java.util.List;

@Document(collection = "goals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goal {
    @Id
    private String goal_id;
    @NotBlank(message = "Le titre est requis")
    @Size(min = 3, message = "Le titre doit comporter au moins 3 caractères")
    private String title;
    @NotBlank(message = "La description est requise")
    @Size(min = 5, message = "La description doit comporter au moins 5 caractères")
    private String description;
    private Date startDate;
    private Date endDate;
    @DBRef
    private List<Reward> rewards;


}
