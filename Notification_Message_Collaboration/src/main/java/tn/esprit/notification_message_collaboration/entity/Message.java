package tn.esprit.notification_message_collaboration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "messages") // Collection MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Message {

    @Id
    private String id; // Identifiant unique MongoDB

    @DBRef
    private User sender; // L'utilisateur qui a envoyé le message

    private String content; // Contenu du message

    private LocalDateTime timestamp; // Date et heure d’envoi

    @DBRef
    private Collaboration collaboration; // Collaboration à laquelle appartient le message
}
