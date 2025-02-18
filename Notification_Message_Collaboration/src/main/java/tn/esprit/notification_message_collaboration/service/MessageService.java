package tn.esprit.notification_message_collaboration.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.notification_message_collaboration.entity.Message;
import tn.esprit.notification_message_collaboration.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Message message) {
        if (messageRepository.existsById(message.getId())) {
            return messageRepository.save(message);
        }
        throw new RuntimeException("Message not found with id: " + message.getId());
    }

    @Override
    public void deleteMessage(String messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public Message retrieveMessageById(String messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + messageId));
    }

    @Override
    public List<Message> retrieveAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> retrieveMessagesByCollaboration(String collaborationId) {
        return messageRepository.findByCollaborationId(collaborationId);
    }

    @Override
    public List<Message> retrieveMessagesBySender(String senderId) {
        return messageRepository.findBySenderId(senderId);
    }
}
