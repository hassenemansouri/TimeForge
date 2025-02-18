package tn.esprit.notification_message_collaboration.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notification_message_collaboration.entity.Collaboration;

import java.util.List;

@Repository
public interface CollaborationRepository extends MongoRepository<Collaboration, String> {
    List<Collaboration> findByParticipantsId(String userId);

    List<Collaboration> findByChatTitleContainingIgnoreCase(String chatTitle);
}
