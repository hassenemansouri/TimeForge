package tn.esprit.notification_message_collaboration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notification_message_collaboration.entity.Notification;
import tn.esprit.notification_message_collaboration.entity.NotificationStatus;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByRecipientId(String recipientId); // Récupérer les notifications d'un utilisateur
    List<Notification> findByRecipientIdAndStatus(String recipientId, NotificationStatus status); // Récupérer les notifications non lues
    List<Notification> findByRelatedEntityId(String relatedEntityId); // Récupérer les notifications liées à une entité
}