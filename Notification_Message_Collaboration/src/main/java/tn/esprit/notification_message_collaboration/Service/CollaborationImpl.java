package tn.esprit.notification_message_collaboration.Service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import tn.esprit.notification_message_collaboration.FullCollaborationResponse;

import tn.esprit.notification_message_collaboration.entity.Collaboration;
import tn.esprit.notification_message_collaboration.repository.CollaborationRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CollaborationImpl implements ICollaboration {

    private CollaborationRepository collaborationRepository;



    public void addCollaboration(Collaboration collaboration) {
        collaboration.setCreatedAt(new Date());
        collaboration.setLastUpdated(LocalDateTime.now());
        collaborationRepository.save(collaboration);
    }


    public List<Collaboration> findAllCollaborations() {
        return collaborationRepository.findAll();
    }

    @Override
    public FullCollaborationResponse findCollaborationsWithUsers(String collaboration_id) {
        return null;
    }

    @Override
    public void updateCollaboration(String collaboration_id, Collaboration updatedCollaboration) {

    }


    public void deleteCollaboration(String collaboration_id) {
        collaborationRepository.deleteById(collaboration_id);
    }
}