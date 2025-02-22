package tn.esprit.notification_message_collaboration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notification_message_collaboration.entity.Collaboration;

@Repository
public interface CollaborationRepository extends MongoRepository<Collaboration, String> {
}
