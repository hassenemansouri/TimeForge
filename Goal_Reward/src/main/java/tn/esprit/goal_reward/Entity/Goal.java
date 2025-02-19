package tn.esprit.goal_reward.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Goal {
    @Id
    private int id;

    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    @DBRef
    private List<Reward> rewards;
}
