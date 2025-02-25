package tn.esprit.notification_message_collaboration.Service;

import tn.esprit.notification_message_collaboration.entity.Message;

import java.util.List;

public interface IMessage {
    Message sendMessage(Message message);
    Message getMessageById(String id);
    List<Message> getMessagesBySenderAndReceiver(String senderId, String receiverId);
    Message updateMessageContent(String id, String newContent);
    void deleteMessage(String id);
    List<Message> getMessageHistory(String receiverId);
}