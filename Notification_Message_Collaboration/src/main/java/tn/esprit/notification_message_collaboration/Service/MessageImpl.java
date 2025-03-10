package tn.esprit.notification_message_collaboration.Service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import tn.esprit.notification_message_collaboration.entity.Message;
import tn.esprit.notification_message_collaboration.entity.MessageStatus;
import tn.esprit.notification_message_collaboration.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class MessageImpl implements IMessage {

    private final MessageRepository messageRepository;

    @Override
    public Message sendMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        message.setStatus(MessageStatus.SENT);
        return messageRepository.save(message);
    }

    @Override
    public Message getMessageById(String id) {
        return messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
    }

    @Override
    public List<Message> getMessagesBySenderAndReceiver(String senderId, String receiverId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    @Override
    public Message updateMessageContent(String id, String newContent) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
        message.setContent(newContent);
        message.setEdited(true);
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(String id) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
        message.setDeleted(true);
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessageHistory(String receiverId) {
        return messageRepository.findByReceiverId(receiverId);
    }
}