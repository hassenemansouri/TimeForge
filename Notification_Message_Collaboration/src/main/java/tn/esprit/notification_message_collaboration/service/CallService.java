package tn.esprit.notification_message_collaboration.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
@Service
public class CallService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public CallService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void inviteToCall(String senderId, String receiverId) {
        messagingTemplate.convertAndSend("/topic/calls/" + receiverId,
                "Invitation d'appel de " + senderId);
    }

    public void acceptCall(String senderId, String receiverId) {
        messagingTemplate.convertAndSend("/topic/calls/" + senderId,
                "Appel accepté par " + receiverId);
    }

    public void rejectCall(String senderId, String receiverId) {
        messagingTemplate.convertAndSend("/topic/calls/" + senderId,
                "Appel rejeté par " + receiverId);
    }
}