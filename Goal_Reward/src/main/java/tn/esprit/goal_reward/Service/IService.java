package tn.esprit.goal_reward.Service;

import tn.esprit.goal_reward.Entity.*;
import tn.esprit.goal_reward.FullGoalResponse;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IService {

    Goal ajouterGoal(Goal goal);

    List<Goal> getGoals();

    FullGoalResponse findGoalWithUsers(String goalId);

    void supprimerGoal(String id);

    Goal modifierGoal(String id, Goal goal);

    Optional<Goal> getGoalById(String id);

    Reward ajouterReward(Reward reward);

    void supprimerReward(String id);

    Reward modifierReward(String id, Reward reward);

    Optional<Reward> getRewardById(String id);

    List<Reward> getAllRewards();

    Categorie addCategorie(Categorie categorie);

    List<Categorie> getAllCategories();

    Optional<Categorie> getCategorieById(String id);

    Categorie updateCategorie(String id, Categorie categorie);

    void deleteCategorie(String id);


    public Long getEstimatedDurationDays(String libelle);

    Goal ajouterGoalAvecNouvellesCategories(Goal goal, List<Categorie> categoriesFromRequest);

    public Date calculateSmartEndDate(Date startDate, List<Categorie> categories);
}