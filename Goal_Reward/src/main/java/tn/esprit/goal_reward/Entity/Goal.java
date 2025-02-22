package tn.esprit.goal_reward.Entity;

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

    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    @DBRef
    private List<Reward> rewards;
}
