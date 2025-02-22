package tn.esprit.notification_message_collaboration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.notification_message_collaboration.FullCollaborationResponse;
import tn.esprit.notification_message_collaboration.Service.CollaborationImpl;
import tn.esprit.notification_message_collaboration.entity.Collaboration;

import java.util.List;

@RestController
@RequestMapping("/api/collaborations")
//http://localhost:8300/timeforge/swagger-ui/index.html#/
public class CollaborationController {
    @Autowired
    private CollaborationImpl service;


    @PostMapping("/add")
    public void addCollaboration(@RequestBody Collaboration collaboration) {  // Ajout de @RequestBody pour Swagger
        service.addCollaboration ( collaboration );
    }

    @GetMapping
    public List<Collaboration> findAllCollaborations() {

        return service.findAllCollaborations ();
    }
    @GetMapping("WithUsers/{collaboration-id}")
    public ResponseEntity<FullCollaborationResponse> findCollaborations(@PathVariable("collaboration-id") String collaboration_id){
        return ResponseEntity.ok (service.findCollaborationsWithUsers(collaboration_id));

    }
}
