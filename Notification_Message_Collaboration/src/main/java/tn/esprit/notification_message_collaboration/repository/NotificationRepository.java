package tn.esprit.notification_message_collaboration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.notification_message_collaboration.model.Notification;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUserId(String userId); // Recherche les notifications d'un utilisateur
}