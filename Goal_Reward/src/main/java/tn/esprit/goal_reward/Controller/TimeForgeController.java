package tn.esprit.goal_reward.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tn.esprit.goal_reward.Entity.*;
import tn.esprit.goal_reward.Repository.CategorieRuleRepository;

import tn.esprit.goal_reward.FullGoalResponse;
import tn.esprit.goal_reward.Repository.CategorieRepository;
import tn.esprit.goal_reward.Repository.GoalRepository;
import tn.esprit.goal_reward.Service.IService;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/goals")
@AllArgsConstructor

public class TimeForgeController {

    @Autowired
    IService service;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    CategorieRuleRepository categorieRuleRepository;
    @Autowired
    private GoalRepository goalRepository;

    @PostMapping("/ajouterGoal")
    public ResponseEntity<?> ajouterGoal(@Valid @RequestBody Goal goal) {
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
    public ResponseEntity<?> modifierGoal(@PathVariable String id, @Valid @RequestBody Goal goal) {
        try {
            Goal updatedGoal = service.modifierGoal(id, goal);
            return ResponseEntity.ok(updatedGoal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/ajouterReward")
    public ResponseEntity<?> ajouterReward(@Valid @RequestBody Reward reward, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors); // Retourne les erreurs au front
        }
        return ResponseEntity.ok(service.ajouterReward(reward));
    }

    @DeleteMapping("/supprimerReward/{id}")
    public void supprimerReward(@PathVariable("id") String id) {
        service.supprimerReward(id);
    }

    @PutMapping("/modifierReward/{id}")
    public ResponseEntity<?> modifierReward(@PathVariable("id") String id, @Valid @RequestBody Reward reward, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(service.modifierReward(id, reward));
    }

    @GetMapping("/getReward/{id}")
    public Optional<Reward> getRewardById(@PathVariable("id") String id) {
        return service.getRewardById(id);
    }

    @GetMapping("/getAllRewards")
    public List<Reward> getAllRewards() {
        return service.getAllRewards();
    }


    @PostMapping("/add")
    public ResponseEntity<Categorie> addCategorie(@RequestBody Categorie categorie) {
        return ResponseEntity.ok(service.addCategorie(categorie));
    }

    @GetMapping("/getAll")
    public List<Categorie> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Categorie>> getCategorie(@PathVariable String id) {
        return ResponseEntity.ok(service.getCategorieById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable String id, @RequestBody Categorie categorie) {
        return ResponseEntity.ok(service.updateCategorie(id, categorie));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategorie(@PathVariable String id) {
        service.deleteCategorie(id);
    }
    @PostMapping("/ajouterGoalAvecNouvellesCategories")
    public ResponseEntity<?> ajouterGoalAvecCategorieEtRule(@RequestBody GoalCreationRequest request) {
        try {
            Goal goal = request.getGoal();
            List<Categorie> categories = request.getCategories();

            Goal goalCree = service.ajouterGoalAvecNouvellesCategories(goal, categories);
            return ResponseEntity.ok(goalCree);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }
    @GetMapping("/estimerDateFin/{libelle}")
    public ResponseEntity<Date> getEstimatedEndDate(@PathVariable String libelle) {
        Categorie cat = new Categorie();
        cat.setLibelle(libelle);
        Date estimated = service.calculateSmartEndDate(new Date(), List.of(cat));
        return ResponseEntity.ok(estimated);
    }
    @GetMapping("/estimerDureeJours/{libelle}")
    public ResponseEntity<Long> getEstimatedDurationInDays(@PathVariable String libelle) {
        long duration = service.getEstimatedDurationDays(libelle);
        return ResponseEntity.ok(duration);
    }

}
