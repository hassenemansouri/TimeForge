package tn.esprit.notification_message_collaboration.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.notification_message_collaboration.Service.IMessage;

import tn.esprit.notification_message_collaboration.entity.Message;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@AllArgsConstructor
public class MessageController {

    private final IMessage imessage;

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(imessage.sendMessage(message));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable String id) {
        return ResponseEntity.ok(imessage.getMessageById(id));
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessagesBySenderAndReceiver(
            @RequestParam String senderId,
            @RequestParam String receiverId) {
        return ResponseEntity.ok(imessage.getMessagesBySenderAndReceiver(senderId, receiverId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessageContent(
            @PathVariable String id,
            @RequestParam String newContent) {
        return ResponseEntity.ok(imessage.updateMessageContent(id, newContent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String id) {
        imessage.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/history/{receiverId}")
    public ResponseEntity<List<Message>> getMessageHistory(@PathVariable String receiverId) {
        return ResponseEntity.ok(imessage.getMessageHistory(receiverId));
    }
}