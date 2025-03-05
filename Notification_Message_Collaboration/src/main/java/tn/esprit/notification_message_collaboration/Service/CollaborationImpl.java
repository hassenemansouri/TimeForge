package tn.esprit.notification_message_collaboration.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.notification_message_collaboration.FullCollaborationResponse;
import tn.esprit.notification_message_collaboration.entity.Collaboration;
import tn.esprit.notification_message_collaboration.entity.Message;
import tn.esprit.notification_message_collaboration.repository.CollaborationRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CollaborationImpl implements ICollaboration {

    private final CollaborationRepository collaborationRepository;

    @Override
    public void addCollaboration(Collaboration collaboration) {
        collaboration.setCreatedAt(new Date());
        collaboration.setLastUpdated(LocalDateTime.now());
        collaborationRepository.save(collaboration);
        log.info("Collaboration ajoutée : {}", collaboration.getChatTitle());
    }

    @Override
    public List<Collaboration> findAllCollaborations() {
        return collaborationRepository.findAll();
    }

    @Override
    public FullCollaborationResponse findCollaborationsWithUsers(String collaborationId) {
        // Implémentation à compléter
        return null;
    }

    @Override
    public void updateCollaboration(String collaborationId, Collaboration updatedCollaboration) {
        Optional<Collaboration> existingCollaboration = collaborationRepository.findById(collaborationId);
        if (existingCollaboration.isPresent()) {
            Collaboration collaboration = existingCollaboration.get();
            collaboration.setChatTitle(updatedCollaboration.getChatTitle());
            collaboration.setDescription(updatedCollaboration.getDescription());
            collaboration.setLastUpdated(LocalDateTime.now());
            collaborationRepository.save(collaboration);
            log.info("Collaboration mise à jour : {}", collaborationId);
        } else {
            throw new RuntimeException("Collaboration non trouvée");
        }
    }

    @Override
    public void deleteCollaboration(String collaborationId) {
        collaborationRepository.deleteById(collaborationId);
        log.info("Collaboration supprimée : {}", collaborationId);
    }

    @Override
    public List<Collaboration> findByChatTitle(String chatTitle) {
        return collaborationRepository.findByChatTitleContainingIgnoreCase(chatTitle);
    }

    @Override
    public List<Collaboration> findByParticipant(String userId) {
        return collaborationRepository.findByParticipantsId(userId); // Utilise "findByParticipantsId"
    }
    @Override
    public void addMessageToCollaboration(String collaborationId, Message message) {
        Optional<Collaboration> collaborationOpt = collaborationRepository.findById(collaborationId);
        if (collaborationOpt.isPresent()) {
            Collaboration collaboration = collaborationOpt.get();
            collaboration.getMessages().add(message);
            collaboration.setLastUpdated(LocalDateTime.now());
            collaborationRepository.save(collaboration);
            log.info("Message ajouté à la collaboration : {}", collaborationId);
        } else {
            throw new RuntimeException("Collaboration non trouvée");
        }
    }
}