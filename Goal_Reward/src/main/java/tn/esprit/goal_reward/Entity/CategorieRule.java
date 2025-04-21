package tn.esprit.goal_reward.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;

@Document(collection = "categorie_rules")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CategorieRule {
    @Id
    private String id;
    private String libelle;

}
