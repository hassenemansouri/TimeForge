package tn.esprit.notification_message_collaboration.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.notification_message_collaboration.Service.ICollaboration;
import tn.esprit.notification_message_collaboration.entity.Collaboration;
import tn.esprit.notification_message_collaboration.entity.Message;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/collaborations")
@AllArgsConstructor
public class CollaborationController {

    private final ICollaboration collaborationService;

    @PostMapping("/add")
    public ResponseEntity<Void> addCollaboration(@RequestBody Collaboration collaboration) {
        collaborationService.addCollaboration(collaboration);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Collaboration>> findAllCollaborations() {
        return ResponseEntity.ok(collaborationService.findAllCollaborations());
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<Collaboration>> findByChatTitle(@RequestParam String chatTitle) {
        return ResponseEntity.ok(collaborationService.findByChatTitle(chatTitle));
    }

    @GetMapping("/search/participant")
    public ResponseEntity<List<Collaboration>> findByParticipant(@RequestParam String userId) {
        return ResponseEntity.ok(collaborationService.findByParticipant(userId));
    }

    @PostMapping("/{collaborationId}/messages")
    public ResponseEntity<Void> addMessageToCollaboration(
            @PathVariable String collaborationId,
            @RequestBody Message message) {
        collaborationService.addMessageToCollaboration(collaborationId, message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{collaborationId}")
    public ResponseEntity<Void> updateCollaboration(
            @PathVariable String collaborationId,
            @RequestBody Collaboration updatedCollaboration) {
        collaborationService.updateCollaboration(collaborationId, updatedCollaboration);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{collaborationId}")
    public ResponseEntity<Void> deleteCollaboration(@PathVariable String collaborationId) {
        collaborationService.deleteCollaboration(collaborationId);
        return ResponseEntity.noContent().build();
    }
}