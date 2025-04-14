package tn.esprit.notification_message_collaboration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import tn.esprit.user_strategicpartership.repository.UserRepository;

@Service
public class ChatService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    private UserRepository userRepository; // Exemple d'usage d'un repository pour récupérer des utilisateurs

    public void processMessage(WebSocketSession session, TextMessage message) {
        // Logique de traitement du message
        System.out.println("Traitement du message : " + message.getPayload());
        String recipient = "user123"; // Exemple d'utilisateur cible
        messagingTemplate.convertAndSendToUser(recipient, "/queue/messages", message.getPayload());
    }

}
