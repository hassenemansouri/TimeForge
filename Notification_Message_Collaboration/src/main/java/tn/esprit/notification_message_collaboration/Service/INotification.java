package tn.esprit.notification_message_collaboration.Service;

import tn.esprit.notification_message_collaboration.entity.Notification;

import java.util.List;

public interface INotification {
    void sendNotification(Notification notification);
    List<Notification> getNotificationsByRecipient(String recipientId);
    List<Notification> getUnreadNotifications(String recipientId);
    void markNotificationAsRead(String notificationId);
    void deleteNotification(String notificationId);
}