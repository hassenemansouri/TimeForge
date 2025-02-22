package tn.esprit.notification_message_collaboration.Service;

import tn.esprit.notification_message_collaboration.FullCollaborationResponse;
import tn.esprit.notification_message_collaboration.entity.Collaboration;

import java.util.List;

public interface ICollaboration {
    void addCollaboration(Collaboration collaboration);
    List<Collaboration> findAllCollaborations();

    FullCollaborationResponse findCollaborationsWithUsers(String collaboration_id);
}
