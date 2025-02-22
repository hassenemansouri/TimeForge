package tn.esprit.notification_message_collaboration.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.notification_message_collaboration.FullCollaborationResponse;
import tn.esprit.notification_message_collaboration.client.UserClient;
import tn.esprit.notification_message_collaboration.entity.Collaboration;
import tn.esprit.notification_message_collaboration.repository.CollaborationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CollaborationImpl implements ICollaboration{

    private CollaborationRepository collaborationRepository;

    private UserClient userClient;

    public void addCollaboration(Collaboration collaboration) {
        collaborationRepository.save(collaboration);
    }

    public List<Collaboration> findAllCollaborations() {
        return collaborationRepository.findAll();
    }

    public FullCollaborationResponse findCollaborationsWithUsers(String collaboration_id) {
        var collaboration = collaborationRepository.findById(collaboration_id)
                .orElse( Collaboration.builder()
                        .chatTitle ( "mansour" )
                        .build());

        var users = userClient.fundAllUsersByCollaboration (collaboration_id); // Corrigé ici

        return FullCollaborationResponse.builder()
                .chatTitle(collaboration.getChatTitle())
                .createdAt(collaboration.getCreatedAt())
                .lastUpdated(collaboration.getLastUpdated())
                .messages(collaboration.getMessages())
                .users(users)
                .build();
    }

}
