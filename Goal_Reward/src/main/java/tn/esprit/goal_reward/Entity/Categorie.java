package tn.esprit.goal_reward.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categorie {
    @Id
    private String categorie_id;
    private String libelle;
    private String description;
    @DBRef
    private List<Goal> goals;
}
