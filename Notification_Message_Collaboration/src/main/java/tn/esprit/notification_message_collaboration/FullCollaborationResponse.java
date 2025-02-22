package tn.esprit.notification_message_collaboration;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import tn.esprit.notification_message_collaboration.client.User;
import tn.esprit.notification_message_collaboration.entity.Message;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullCollaborationResponse {

    private String chatTitle; // Nom de la collaboration
    private Date createdAt; // Date de création
    private LocalDateTime lastUpdated; // Dernière mise à jour
    private List<Message> messages; // Liste des messages envoyés
    private List<User> users;
}
