package tn.esprit.notification_message_collaboration.WebSocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import tn.esprit.notification_message_collaboration.model.Message;
import tn.esprit.notification_message_collaboration.service.MessageService;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat")
    public void send(Message message) {
        messageService.saveMessage(message);
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
