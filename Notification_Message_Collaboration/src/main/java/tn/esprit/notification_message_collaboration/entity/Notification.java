package tn.esprit.notification_message_collaboration.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications") // Collection MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    private String notificationId; // Identifiant unique MongoDB

    @NotBlank(message = "Le contenu de la notification est obligatoire")
    private String content; // Contenu de la notification

    @NotBlank(message = "L'ID du destinataire est obligatoire")
    private String recipientId; // ID de l'utilisateur qui reçoit la notification

    private String senderId; // ID de l'expéditeur (utilisateur ou système)

    private LocalDateTime timestamp; // Date et heure de la notification

    private NotificationStatus status; // Statut de la notification

    private NotificationType type; // Type de notification

    private String relatedEntityId; // ID de l'entité liée (message, collaboration, etc.)

    private boolean isRead; // Indique si la notification a été lue
}

