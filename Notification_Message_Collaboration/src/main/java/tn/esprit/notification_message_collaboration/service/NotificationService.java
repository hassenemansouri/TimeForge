package tn.esprit.notification_message_collaboration.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import tn.esprit.notification_message_collaboration.model.Notification;
import tn.esprit.notification_message_collaboration.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    private NotificationRepository notificationRepository;
    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Envoi de la notification à l'utilisateur
    // Envoyer une notification (exemple avec WebSocket)
    public void sendNotification(String userId, String message) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setRead(false);
        notificationRepository.save(notification);
        // Logique pour envoyer la notification via WebSocket (ou autre)
    }

    // Marquer une notification comme lue
    public Notification markAsRead(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        return notificationRepository.save(notification);
    }
    // Récupérer les notifications d'un utilisateur
    public List<Notification> getNotifications(String userId) {
        return notificationRepository.findByUserId(userId);
    }


}
