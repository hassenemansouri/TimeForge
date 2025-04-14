package tn.esprit.goal_reward.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "rewards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reward {
    @Id
    private String id;
    @NotBlank(message = "Le type est obligatoire.")
    @Size(min = 3, max = 100, message = "Le type doit contenir entre 3 et 100 caractères.")
    private String type;
    @NotBlank(message = "La description est obligatoire.")
    @Size(min = 5, max = 500, message = "La description doit contenir entre 5 et 500 caractères.")
    private String description;
    @NotNull(message = "La date est obligatoire.")
    @FutureOrPresent(message = "La date doit être aujourd'hui ou une date future.")
    private Date dateAwarded;

}