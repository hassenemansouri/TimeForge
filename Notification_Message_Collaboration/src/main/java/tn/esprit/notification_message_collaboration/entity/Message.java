package tn.esprit.notification_message_collaboration.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages") // Collection MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    private String messageId; // Identifiant unique MongoDB

    private String content; // Contenu du message
    private String senderId; // ID de l'expéditeur
    private String receiverId; // ID du destinataire
    private LocalDateTime timestamp; // Date et heure d'envoi
    private MessageStatus status; // Statut du message (envoyé, livré, lu)
    private MessageType type; // Type de message (texte, image, vidéo, fichier)
    private String parentMessageId; // ID du message parent (optionnel)
    private boolean isEdited; // Indique si le message a été modifié
    private boolean isDeleted; // Indique si le message a été supprimé (soft delete)
}

