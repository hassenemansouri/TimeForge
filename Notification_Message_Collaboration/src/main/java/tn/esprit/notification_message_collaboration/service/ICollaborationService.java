package tn.esprit.notification_message_collaboration.service;


import tn.esprit.notification_message_collaboration.entity.Collaboration;

import java.util.List;

public interface ICollaborationService {
    Collaboration addCollaboration(Collaboration collaboration); // Ajouter une collaboration
    Collaboration updateCollaboration(Collaboration collaboration); // Mettre à jour une collaboration
    void deleteCollaboration(String collaborationId); // Supprimer une collaboration
    Collaboration retrieveCollaborationById(String collaborationId); // Récupérer une collaboration par son ID
    List<Collaboration> retrieveAllCollaborations(); // Récupérer toutes les collaborations
    List<Collaboration> retrieveCollaborationsByUser(String userId); // Récupérer les collaborations d'un utilisateur
    List<Collaboration> retrieveCollaborationsByChatTitle(String chatTitle); // Rechercher des collaborations par titre de chat
}
