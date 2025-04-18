package tn.esprit.notification_message_collaboration.repository;






import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.notification_message_collaboration.model.Message;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
