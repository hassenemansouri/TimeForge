package tn.esprit.goal_reward.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.goal_reward.Entity.Goal;
import tn.esprit.goal_reward.Service.IService;

@RestController
@RequestMapping("/timeforge")
@AllArgsConstructor
public class TimeForgeController {

    @Autowired
    IService service;

    @PostMapping("/ajouterGoal")
    public Goal ajouterGoal(@RequestBody Goal goal) {
        return service.ajouterGoal(goal);
    }
}