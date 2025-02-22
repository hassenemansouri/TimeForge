package tn.esprit.goal_reward;

import lombok.*;
import tn.esprit.goal_reward.client.User;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullGoalResponse {
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private List<User> users;
}
