package tn.esprit.goal_reward.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(name = "user-service" , url = "${application.config.users-url}")
public interface UserClient {

    @GetMapping("/goal/{goal-id}")
    List<User> fundAllUsersByGoal(@PathVariable("goal-id") String goal_id);

}
