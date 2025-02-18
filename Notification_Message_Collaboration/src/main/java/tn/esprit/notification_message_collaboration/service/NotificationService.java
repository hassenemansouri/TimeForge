package tn.esprit.notification_message_collaboration.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.notification_message_collaboration.entity.Notification;
import tn.esprit.notification_message_collaboration.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        if (notificationRepository.existsById(notification.getId())) {
            return notificationRepository.save(notification);
        }
        throw new RuntimeException("Notification not found with id: " + notification.getId());
    }

    @Override
    public void deleteNotification(String notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public Notification retrieveNotificationById(String notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + notificationId));
    }

    @Override
    public List<Notification> retrieveAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> retrieveNotificationsByRecipient(String recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }

    @Override
    public List<Notification> retrieveNotificationsByRecipientAndReadStatus(String recipientId, boolean isRead) {
        return notificationRepository.findByRecipientIdAndIsRead(recipientId, isRead);
    }

    @Override
    public List<Notification> retrieveNotificationsBySender(String senderId) {
        return notificationRepository.findBySenderId(senderId);
    }
}
