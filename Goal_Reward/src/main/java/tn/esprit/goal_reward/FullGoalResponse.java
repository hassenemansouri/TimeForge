package tn.esprit.goal_reward;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
