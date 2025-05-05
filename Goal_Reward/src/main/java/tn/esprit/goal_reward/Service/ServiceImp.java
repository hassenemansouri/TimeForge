package tn.esprit.goal_reward.Service;

import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.goal_reward.Entity.*;
import tn.esprit.goal_reward.Repository.CategorieRepository;
import tn.esprit.goal_reward.Repository.CategorieRuleRepository;
import tn.esprit.goal_reward.Repository.GoalRepository;
import tn.esprit.goal_reward.Repository.RewardRepository;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceImp implements IService {

    private GoalRepository goalRepository;
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
            goal.setEndDate(calculateSmartEndDate(goal.getStartDate(), goal.getCategories()));
        }
        return goalRepository.save(goal);
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
            goal.setEndDate(calculateSmartEndDate(goal.getStartDate(), categoriesToAssign));
        }

        return goalRepository.save(goal);
    }
    public Date calculateSmartEndDate(Date startDate, List<Categorie> categories) {
        if (categories == null || categories.isEmpty()) {
            Calendar defaultCal = Calendar.getInstance();
            defaultCal.setTime(startDate);
            defaultCal.add(Calendar.DAY_OF_YEAR, 3); // fallback par défaut
            return defaultCal.getTime();
        }

        List<Goal> allGoals = goalRepository.findAll();
        List<Long> durations = new ArrayList<>();

        for (Categorie cat : categories) {
            String libelle = cat.getLibelle().toLowerCase().trim();
            for (Goal g : allGoals) {
                if (g.getCategories() != null && g.getStartDate() != null && g.getEndDate() != null) {
                    boolean hasSameLibelle = g.getCategories().stream()
                            .anyMatch(c -> c.getLibelle().equalsIgnoreCase(libelle));
                    if (hasSameLibelle) {
                        long days = ChronoUnit.DAYS.between(
                                g.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                g.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        if (days >= 0) durations.add(days);
                    }
                }
            }
        }

        long avgDays = durations.isEmpty() ? 3 : (long) durations.stream().mapToLong(d -> d).average().orElse(3);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_YEAR, (int) avgDays);

        return cal.getTime();
    }
    @Override
    public Long getEstimatedDurationDays(String libelle) {
        List<Goal> allGoals = goalRepository.findAll();
        List<Long> durations = new ArrayList<>();

        String target = libelle.trim().toLowerCase();

        for (Goal goal : allGoals) {
            if (goal.getStartDate() == null || goal.getEndDate() == null) continue;

            List<Categorie> categories = goal.getCategories();
            if (categories == null || categories.isEmpty()) continue;

            boolean hasLibelle = categories.stream()
                    .filter(c -> c.getLibelle() != null)
                    .map(c -> c.getLibelle().trim().toLowerCase())
                    .anyMatch(l -> l.equals(target));

            if (hasLibelle) {
                long days = ChronoUnit.DAYS.between(
                        goal.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        goal.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                );

                durations.add(days);
                System.out.println("📌 Goal: " + goal.getTitle() + " → " + days + " jours");
            }
        }

        if (durations.size() < 3) {
            System.out.println("⚠️ Pas assez de goals pour le libellé: " + libelle + " (trouvé " + durations.size() + ")");
            return null;
        }

        double avg = durations.stream().mapToLong(Long::longValue).average().orElse(0);
        long rounded = Math.round(avg);
        System.out.println("✅ Moyenne réelle pour '" + libelle + "' = " + rounded + " jours");
        return rounded;
    }

    public Map<String, Object> getDashboardStats() {
        List<Goal> goals = goalRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        // Statistiques mensuelles : nombre de créations de goals par mois
        Map<String, Long> goalsPerMonth = goals.stream()
                .filter(g -> g.getStartDate() != null)
                .collect( Collectors.groupingBy(
                        g -> g.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        Collectors.counting()
                ));

        // Moyenne des catégories par goal par mois
        Map<String, Double> avgCategoriesPerMonth = goals.stream()
                .filter(g -> g.getStartDate() != null)
                .collect(Collectors.groupingBy(
                        g -> g.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        Collectors.averagingInt(g -> g.getCategories() != null ? g.getCategories().size() : 0)
                ));

        // Répartition des goals par taille de catégories
        Map<String, Long> goalsBySize = goals.stream()
                .collect(Collectors.groupingBy(
                        g -> {
                            int size = g.getCategories() != null ? g.getCategories().size() : 0;
                            if (size <= 3) return "Petit (<=3)";
                            else if (size <= 6) return "Moyen (4-6)";
                            else return "Grand (>6)";
                        },
                        Collectors.counting()
                ));

        // Compilation des statistiques
        Map<String, Object> stats = new HashMap<>();
        stats.put("goalsPerMonth", goalsPerMonth);
        stats.put("avgCategoriesPerMonth", avgCategoriesPerMonth);
        stats.put("goalsBySize", goalsBySize);
        stats.put("totalGoals", goals.size());

        return stats;
    }

}

