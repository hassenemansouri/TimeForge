package tn.esprit.notification_message_collaboration.Service;

import tn.esprit.notification_message_collaboration.FullCollaborationResponse;
import tn.esprit.notification_message_collaboration.entity.Collaboration;
import tn.esprit.notification_message_collaboration.entity.Message;


import java.util.List;

public interface ICollaboration {
    void addCollaboration(Collaboration collaboration);
    List<Collaboration> findAllCollaborations();
    FullCollaborationResponse findCollaborationsWithUsers(String collaborationId);
    void updateCollaboration(String collaborationId, Collaboration updatedCollaboration);
    void deleteCollaboration(String collaborationId);
    List<Collaboration> findByChatTitle(String chatTitle); // Recherche par titre
    List<Collaboration> findByParticipant(String userId); // Recherche par participant
    void addMessageToCollaboration(String collaborationId, Message message); // Ajouter un message
}