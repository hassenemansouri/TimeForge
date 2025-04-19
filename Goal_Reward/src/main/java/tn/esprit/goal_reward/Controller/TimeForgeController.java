package tn.esprit.goal_reward.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tn.esprit.goal_reward.Entity.*;
import tn.esprit.goal_reward.Repository.CategorieRuleRepository;

import tn.esprit.goal_reward.FullGoalResponse;
import tn.esprit.goal_reward.Repository.CategorieRepository;
import tn.esprit.goal_reward.Repository.GoalRepository;
import tn.esprit.goal_reward.Service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
            // Création et sauvegarde de la nouvelle catégorie sans champ `field` et `amount`
            Categorie newCat = Categorie.builder()
                    .libelle(request.getCategorie().getLibelle())
                    .description(request.getCategorie().getDescription())
                    .build();

            Categorie savedCat = categorieRepository.save(newCat);

            // Lier la catégorie au Goal
            Goal goal = request.getGoal();
            goal.setCategories(List.of(savedCat));

            // Calcul de la date de fin (endDate) si non fournie
            if (goal.getStartDate() != null && goal.getEndDate() == null) {
                goal.setEndDate(service.calculateEndDate(goal.getStartDate(), List.of(savedCat)));
            }

            Goal savedGoal = goalRepository.save(goal);
            return ResponseEntity.ok(savedGoal);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }

}
