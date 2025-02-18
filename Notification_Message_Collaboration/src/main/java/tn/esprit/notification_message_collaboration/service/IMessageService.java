package tn.esprit.notification_message_collaboration.service;



import tn.esprit.notification_message_collaboration.entity.Message;

import java.util.List;

public interface IMessageService {
    Message addMessage(Message message); // Ajouter un message
    Message updateMessage(Message message); // Mettre à jour un message
    void deleteMessage(String messageId); // Supprimer un message
    Message retrieveMessageById(String messageId); // Récupérer un message par son ID
    List<Message> retrieveAllMessages(); // Récupérer tous les messages
    List<Message> retrieveMessagesByCollaboration(String collaborationId); // Récupérer les messages d'une collaboration
    List<Message> retrieveMessagesBySender(String senderId); // Récupérer les messages envoyés par un utilisateur
}
