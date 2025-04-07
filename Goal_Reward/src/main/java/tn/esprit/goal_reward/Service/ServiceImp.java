package tn.esprit.goal_reward.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.goal_reward.Entity.Categorie;
import tn.esprit.goal_reward.Entity.Goal;
import tn.esprit.goal_reward.Entity.Reward;
import tn.esprit.goal_reward.FullGoalResponse;
import tn.esprit.goal_reward.Repository.CategorieRepository;
import tn.esprit.goal_reward.Repository.GoalRepository;
import tn.esprit.goal_reward.Repository.RewardRepository;
import tn.esprit.goal_reward.client.UserClient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceImp implements IService {

    private GoalRepository goalRepository;
    private UserClient userClient;
    private RewardRepository rewardRepository;
    private CategorieRepository categorieRepository;

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



    private Date calculateEndDate(Date startDate, List<Categorie> categories) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        if (categories != null && !categories.isEmpty()) {
            for (Categorie categorie : categories) {
                String categoryName = categorie.getLibelle().toLowerCase();

                switch (categoryName) {
                    case "urgence":
                        calendar.add(Calendar.HOUR, 12); // Tâches urgentes: 12 heures
                        break;
                    case "pomodoro":
                        calendar.add(Calendar.MINUTE, 25); // Session Pomodoro: 25 min
                        break;
                    case "projet court":
                        calendar.add(Calendar.DAY_OF_YEAR, 7); // 1 semaine
                        break;
                    case "projet moyen":
                        calendar.add(Calendar.MONTH, 1); // 1 mois
                        break;
                    case "projet long":
                        calendar.add(Calendar.MONTH, 6); // 6 mois
                        break;
                    case "objectif perseonnl":
                        calendar.add(Calendar.MONTH, 3); // 3 mois
                        break;
                    case "objectif professionnel":
                        calendar.add(Calendar.MONTH, 12); // 1 an
                        break;
                    case "santé":
                        calendar.add(Calendar.DAY_OF_YEAR, 1); // Rappels santé: 1 jour
                        break;
                    case "collaboration":
                        calendar.add(Calendar.DAY_OF_YEAR, 14); // Projets collaboratifs: 2 semaines
                        break;
                    case "apprentissage":
                        calendar.add(Calendar.WEEK_OF_YEAR, 4); // Formation: 4 semaines
                        break;
                    case "milestone":
                        calendar.add(Calendar.MONTH, 2); // Jalons projet: 2 mois
                        break;
                    case "analyse productivité":
                        calendar.add(Calendar.DAY_OF_YEAR, 30); // Analyse mensuelle
                        break;
                    default:
                        calendar.add(Calendar.DAY_OF_YEAR, 3); // Par défaut: 3 jours
                }
            }
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Durée par défaut si aucune catégorie: 1 jour
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
}