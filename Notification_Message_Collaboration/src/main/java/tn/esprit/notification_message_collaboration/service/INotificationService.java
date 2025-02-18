package tn.esprit.notification_message_collaboration.service;






import tn.esprit.notification_message_collaboration.entity.Notification;

import java.util.List;

public interface INotificationService {
    Notification addNotification(Notification notification); // Ajouter une notification
    Notification updateNotification(Notification notification); // Mettre à jour une notification
    void deleteNotification(String notificationId); // Supprimer une notification
    Notification retrieveNotificationById(String notificationId); // Récupérer une notification par son ID
    List<Notification> retrieveAllNotifications(); // Récupérer toutes les notifications
    List<Notification> retrieveNotificationsByRecipient(String recipientId); // Récupérer les notifications d'un utilisateur
    List<Notification> retrieveNotificationsByRecipientAndReadStatus(String recipientId, boolean isRead); // Récupérer les notifications lues/non lues
    List<Notification> retrieveNotificationsBySender(String senderId); // Récupérer les notifications envoyées par un utilisateur
}
