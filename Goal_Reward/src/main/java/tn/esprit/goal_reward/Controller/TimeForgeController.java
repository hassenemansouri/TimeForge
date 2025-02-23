package tn.esprit.goal_reward.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.goal_reward.Entity.Goal;
import tn.esprit.goal_reward.FullGoalResponse;
import tn.esprit.goal_reward.Service.IService;

import java.util.List;

@RestController
@RequestMapping("/goals")
@AllArgsConstructor
public class TimeForgeController {

    @Autowired
    IService service;

    @PostMapping("/ajouterGoal")
    public Goal ajouterGoal(@RequestBody Goal goal) {
        return service.ajouterGoal(goal);
    }
    @GetMapping()
    public List<Goal> getAllGoals() {
        return service.getGoals();
    }
    @GetMapping("WithUsers/{goal-id}")
    public ResponseEntity<FullGoalResponse> findCollaborations(@PathVariable("goal-id") String goal_id){
        return ResponseEntity.ok (service.findGoalWithUsers(goal_id));

    }

}