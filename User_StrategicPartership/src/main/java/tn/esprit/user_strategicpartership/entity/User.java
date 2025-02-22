package tn.esprit.user_strategicpartership.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;  // Correction : MongoDB utilise souvent un String pour l'ID
    private String name;
    private String email;
    private String workspaceId;
}
