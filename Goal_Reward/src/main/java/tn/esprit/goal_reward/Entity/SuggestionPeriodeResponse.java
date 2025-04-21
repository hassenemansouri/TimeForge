package tn.esprit.goal_reward.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionPeriodeResponse {
    private String libelle;
    private long moyenne; // moyenne estimée en jours
    private List<String[]> periodes; // liste [dateDebut, dateFin]
}

