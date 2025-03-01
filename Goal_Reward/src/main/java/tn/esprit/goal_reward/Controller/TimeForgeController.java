package tn.esprit.goal_reward.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.goal_reward.Entity.Goal;
import tn.esprit.goal_reward.Entity.Reward;
import tn.esprit.goal_reward.FullGoalResponse;
import tn.esprit.goal_reward.Service.IService;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/goals")
@AllArgsConstructor
public class TimeForgeController {

    @Autowired
    IService service;

    @PostMapping("/ajouterGoal")
    public ResponseEntity<?> ajouterGoal(@RequestBody Goal goal) {
        try {
            Goal newGoal = service.ajouterGoal(goal);
            return ResponseEntity.ok(newGoal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getall")
    public List<Goal> getAllGoals() {

        return service.getGoals();
    }
    @GetMapping("/getGoal/{id}")
    public Optional<Goal> getGoalById(@PathVariable String id) {
        return service.getGoalById(id);
    }

    @GetMapping("WithUsers/{goal-id}")
    public ResponseEntity<FullGoalResponse> findCollaborations(@PathVariable("goal-id") String goal_id){
        return ResponseEntity.ok (service.findGoalWithUsers(goal_id));
    }

    @DeleteMapping("/supprimerGoal/{id}")
    public void supprimerGoal(@PathVariable String id) {
        service.supprimerGoal(id);
    }

    @PutMapping("/modifierGoal/{id}")
    public Goal modifierGoal(@PathVariable String id, @RequestBody Goal goal) {
        return service.modifierGoal(id, goal);
    }

    @PostMapping("/ajouterReward")
    public Reward ajouterReward(@RequestBody Reward reward) {
        return service.ajouterReward(reward);
    }

    @DeleteMapping("/supprimerReward/{id}")
    public void supprimerReward(@PathVariable("id") String id) {
        service.supprimerReward(id);
    }

    @PutMapping("/modifierReward/{id}")
    public Reward modifierReward(@PathVariable("id") String id, @RequestBody Reward reward) {
        return service.modifierReward(id, reward);
    }

    @GetMapping("/getReward/{id}")
    public Optional<Reward> getRewardById(@PathVariable("id") String id) {
        return service.getRewardById(id);
    }

    @GetMapping("/getAllRewards")
    public List<Reward> getAllRewards() {
        return service.getAllRewards();
    }

}