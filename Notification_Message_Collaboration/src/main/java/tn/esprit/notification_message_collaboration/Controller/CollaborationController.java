package tn.esprit.notification_message_collaboration.Controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.notification_message_collaboration.FullCollaborationResponse;
import tn.esprit.notification_message_collaboration.Service.CollaborationImpl;
import tn.esprit.notification_message_collaboration.entity.Collaboration;

import java.util.List;

@RestController
@RequestMapping("/collaborations")
@AllArgsConstructor
public class CollaborationController {

    private CollaborationImpl service;

    @PostMapping("/add")
    public void addCollaboration(@RequestBody Collaboration collaboration) {
        service.addCollaboration(collaboration);
    }

    @GetMapping
    public List<Collaboration> findAllCollaborations() {
        return service.findAllCollaborations();
    }

    @GetMapping("WithUsers/{collaboration-id}")
    public ResponseEntity<FullCollaborationResponse> findCollaborations(@PathVariable("collaboration-id") String collaboration_id) {
        return ResponseEntity.ok(service.findCollaborationsWithUsers(collaboration_id));
    }

    @PutMapping("/update/{collaboration-id}")
    public void updateCollaboration(@PathVariable("collaboration-id") String collaboration_id, @RequestBody Collaboration updatedCollaboration) {
        service.updateCollaboration(collaboration_id, updatedCollaboration);
    }

    @DeleteMapping("/delete/{collaboration-id}")
    public void deleteCollaboration(@PathVariable("collaboration-id") String collaboration_id) {
        service.deleteCollaboration(collaboration_id);
    }
}