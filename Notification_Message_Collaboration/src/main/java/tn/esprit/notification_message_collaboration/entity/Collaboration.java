package tn.esprit.notification_message_collaboration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Document(collection = "collaborations") // Collection MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Collaboration {

    @Id
    private String id; // Identifiant unique MongoDB

    private String chatTitle; // Nom de la collaboration
    private Date createdAt; // Date de création
    private LocalDateTime lastUpdated; // Dernière mise à jour

    @DBRef
    private List<User> participants; // Liste des utilisateurs impliqués

    @DBRef
    private List<Message> messages; // Liste des messages envoyés


}
