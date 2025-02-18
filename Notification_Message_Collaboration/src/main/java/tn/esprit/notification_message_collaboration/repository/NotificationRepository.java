package tn.esprit.notification_message_collaboration.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notification_message_collaboration.entity.Notification;


import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByRecipientId(String recipientId);

    List<Notification> findByRecipientIdAndIsRead(String recipientId, boolean isRead);

    List<Notification> findBySenderId(String senderId);
}
