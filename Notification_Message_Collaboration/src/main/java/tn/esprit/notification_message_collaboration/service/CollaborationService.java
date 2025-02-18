package tn.esprit.notification_message_collaboration.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.notification_message_collaboration.entity.Collaboration;
import tn.esprit.notification_message_collaboration.repository.CollaborationRepository;

import java.util.List;

@Service
public class CollaborationService implements ICollaborationService {

    @Autowired
    private CollaborationRepository collaborationRepository;

    @Override
    public Collaboration addCollaboration(Collaboration collaboration) {
        return collaborationRepository.save(collaboration);
    }

    @Override
    public Collaboration updateCollaboration(Collaboration collaboration) {
        if (collaborationRepository.existsById(collaboration.getId())) {
            return collaborationRepository.save(collaboration);
        }
        throw new RuntimeException("Collaboration not found with id: " + collaboration.getId());
    }

    @Override
    public void deleteCollaboration(String collaborationId) {
        collaborationRepository.deleteById(collaborationId);
    }

    @Override
    public Collaboration retrieveCollaborationById(String collaborationId) {
        return collaborationRepository.findById(collaborationId)
                .orElseThrow(() -> new RuntimeException("Collaboration not found with id: " + collaborationId));
    }

    @Override
    public List<Collaboration> retrieveAllCollaborations() {
        return collaborationRepository.findAll();
    }

    @Override
    public List<Collaboration> retrieveCollaborationsByUser(String userId) {
        return collaborationRepository.findByParticipantsId(userId);
    }

    @Override
    public List<Collaboration> retrieveCollaborationsByChatTitle(String chatTitle) {
        return collaborationRepository.findByChatTitleContainingIgnoreCase(chatTitle);
    }
}
