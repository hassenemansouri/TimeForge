package tn.esprit.notification_message_collaboration.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.notification_message_collaboration.entity.Notification;
import tn.esprit.notification_message_collaboration.entity.NotificationStatus;
import tn.esprit.notification_message_collaboration.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationImpl implements INotification {

    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        notification.setStatus(NotificationStatus.UNREAD);
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsByRecipient(String recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }

    @Override
    public List<Notification> getUnreadNotifications(String recipientId) {
        return notificationRepository.findByRecipientIdAndStatus(recipientId, NotificationStatus.UNREAD);
    }

    @Override
    public void markNotificationAsRead(String notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification non trouvée"));
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(String notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}