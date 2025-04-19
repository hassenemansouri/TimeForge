package tn.esprit.goal_reward.Service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.goal_reward.Entity.*;
import tn.esprit.goal_reward.FullGoalResponse;
import tn.esprit.goal_reward.Repository.CategorieRepository;
import tn.esprit.goal_reward.Repository.CategorieRuleRepository;
import tn.esprit.goal_reward.Repository.GoalRepository;
import tn.esprit.goal_reward.Repository.RewardRepository;
import tn.esprit.goal_reward.client.UserClient;

import java.util.*;

@Service
@AllArgsConstructor
public class ServiceImp implements IService {

    private GoalRepository goalRepository;
    private UserClient userClient;
    private RewardRepository rewardRepository;
    private CategorieRepository categorieRepository;
    private CategorieRuleRepository categorieRuleRepository;

    // Fonction existante pour ajouter un Goal
    public Goal ajouterGoal(Goal goal) {
        if (goal == null || goal.getTitle() == null || goal.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Le titre du goal est obligatoire.");
        }

        if (goal.getDescription() == null || goal.getDescription().isEmpty()) {
            throw new IllegalArgumentException("La description du goal est obligatoire.");
        }

        // Vérifie si la date de début est fournie et que la date de fin est absente
        if (goal.getStartDate() != null && goal.getEndDate() == null) {
            goal.setEndDate(calculateEndDate(goal.getStartDate(), goal.getCategories()));
        }
        return goalRepository.save(goal);
    }
    private static final Map<String, DurationRule> durationRules = new HashMap<>();

    @PostConstruct
    public void loadRulesFromDb() {
        List<CategorieRule> rules = categorieRuleRepository.findAll();
        for (CategorieRule rule : rules) {
            durationRules.put(rule.getLibelle().toLowerCase().trim(),
                    new DurationRule(rule.getField(), rule.getAmount()));
        }
    }
    public Date calculateEndDate(Date startDate, List<Categorie> categories) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        if (categories != null && !categories.isEmpty()) {
            for (Categorie categorie : categories) {
                String key = categorie.getLibelle().toLowerCase().trim();
                DurationRule rule = durationRules.getOrDefault(key, new DurationRule(Calendar.DAY_OF_YEAR, 3)); // Valeur par défaut de 3 jours
                rule.apply(calendar);  // Applique la règle de durée sur le calendrier
            }
        } else {
            // Aucune catégorie => règle par défaut
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Si aucune catégorie, ajoute 1 jour
        }

        return calendar.getTime();
    }

    public List<Goal> getGoals() {
        return goalRepository.findAll();
    }

    public void supprimerGoal(String id) {
        goalRepository.deleteById(id);
    }

    public Goal modifierGoal(String id, Goal goal) {
        if (goalRepository.existsById(id)) {
            goal.setGoal_id(id); // Assigner l'ID existant
            return goalRepository.save(goal);
        }
        return null; // Ou lever une exception
    }

    public Optional<Goal> getGoalById(String id) {
        return goalRepository.findById(id);
    }

    public FullGoalResponse findGoalWithUsers(String goal_id) {
        var goal = goalRepository.findById(goal_id)
                .orElse(Goal.builder()
                        .title("")
                        .description("")
                        .build());

        var users = userClient.fundAllUsersByGoal(goal_id);

        return FullGoalResponse.builder()
                .title(goal.getTitle())
                .description(goal.getDescription())
                .users(users)
                .build();
    }

    public Reward ajouterReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public void supprimerReward(String id) {
        rewardRepository.deleteById(id);
    }

    public Reward modifierReward(String id, Reward reward) {
        if (rewardRepository.existsById(id)) {
            reward.setId(id);
            return rewardRepository.save(reward);
        }
        return null; // Ou lever une exception
    }

    public Optional<Reward> getRewardById(String id) {
        return rewardRepository.findById(id);
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    @Override
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Optional<Categorie> getCategorieById(String id) {
        return categorieRepository.findById(id);
    }

    @Override
    public Categorie updateCategorie(String id, Categorie categorie) {
        if (categorieRepository.existsById(id)) {
            categorie.setCategorie_id(id);
            return categorieRepository.save(categorie);
        }
        return null;
    }

    @Override
    public void deleteCategorie(String id) {
        categorieRepository.deleteById(id);
    }
    public Goal ajouterGoalAvecNouvellesCategories(Goal goal, List<Categorie> categoriesFromRequest) {
        if (goal == null || goal.getTitle() == null || goal.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Le titre du goal est obligatoire.");
        }

        if (goal.getDescription() == null || goal.getDescription().isEmpty()) {
            throw new IllegalArgumentException("La description du goal est obligatoire.");
        }

        // Ajouter ou récupérer les catégories existantes
        List<Categorie> categoriesToAssign = categoriesFromRequest.stream().map(categorie -> {
            if (categorie.getCategorie_id() != null && categorieRepository.findById(categorie.getCategorie_id()).isPresent()) {
                return categorieRepository.findById(categorie.getCategorie_id()).get();
            } else {
                return categorieRepository.save(Categorie.builder()
                        .libelle(categorie.getLibelle())
                        .description(categorie.getDescription())
                        .build());
            }
        }).toList();

        // Assigner les catégories au goal
        goal.setCategories(categoriesToAssign);

        // Calcul de la date de fin en fonction des catégories
        if (goal.getStartDate() != null && goal.getEndDate() == null) {
            goal.setEndDate(calculateEndDate(goal.getStartDate(), categoriesToAssign));
        }

        return goalRepository.save(goal);
    }

}

