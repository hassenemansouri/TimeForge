package tn.esprit.user_strategicpartership.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
//http://localhost:8100/timeforge/swagger-ui/index.html#/
public class UserController {

    private UserServiceImpl userService;  // Injection avec @Autowired

    @Operation(summary = "Ajouter un utilisateur")
    @PostMapping("/add")
    public void addUser(@RequestBody User user) {  // Ajout de @RequestBody pour Swagger
        userService.addUser(user);
    }

    @Operation(summary = "Lister tous les utilisateurs")
    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/workspace/{workspace-id}")
    public ResponseEntity<List<User>> findAllUsers(@PathVariable("workspace-id") String workspaceId){
        return ResponseEntity.ok (userService.findUsersByWorkspace(workspaceId));

    }
    @GetMapping("/collaboration/{collaboration-id}")
    public ResponseEntity<List<User>> findAllUsersByCollab(@PathVariable("collaboration-id") String collaborationId){
        return ResponseEntity.ok (userService.findUsersCollab(collaborationId));

    }
}
