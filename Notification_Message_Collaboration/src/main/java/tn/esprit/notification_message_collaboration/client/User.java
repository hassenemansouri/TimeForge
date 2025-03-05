package tn.esprit.notification_message_collaboration.client;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users") // Collection MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String id; // Identifiant unique MongoDB

    private String username;
    private String email;
    // Autres propriétés...
}