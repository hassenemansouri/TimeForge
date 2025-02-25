package tn.esprit.notification_message_collaboration.entity;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
@Document(collection = "messages") // Collection MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    private String messageId; // Identifiant unique MongoDB

    @NotBlank(message = "Le contenu du message est obligatoire")
    @Size(max = 1000, message = "Le contenu ne doit pas dépasser 1000 caractères")
    private String content; // Contenu du message

    @NotBlank(message = "L'ID de l'expéditeur est obligatoire")
    private String senderId; // ID de l'expéditeur

    @NotBlank(message = "L'ID du destinataire est obligatoire")
    private String receiverId; // ID du destinataire

    private LocalDateTime timestamp; // Date et heure d'envoi

    private MessageStatus status; // Statut du message

    private MessageType type; // Type de message

    private String parentMessageId; // ID du message parent (optionnel)

    private boolean isEdited; // Indique si le message a été modifié

    private boolean isDeleted; // Indique si le message a été supprimé (soft delete)

    private boolean isPinned; // Indique si le message est épinglé

    private String notificationId; // ID de la notification associée (optionnel)
}

