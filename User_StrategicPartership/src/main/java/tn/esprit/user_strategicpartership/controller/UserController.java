package tn.esprit.user_strategicpartership.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
//http://localhost:8100/timeforge/swagger-ui/index.html#/
public class UserController {

    private UserService userService;  // Injection avec @Autowired


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Ajouter un utilisateur")
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {  // Ajout de @RequestBody pour Swagger

        try {
            User createdWorkflow = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkflow);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }

    @Operation(summary = "Lister tous les utilisateurs")
    @GetMapping("/allusers")
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
    @GetMapping("/goal/{goal-id}")
    public ResponseEntity<List<User>> findAllUsersByGoal(@PathVariable("goal-id") String goalId){
        return ResponseEntity.ok (userService.findUsersByGoal(goalId));

    }
    @GetMapping("/project/{project-id}")
    public ResponseEntity<List<User>> findAllUsersByProjects(@PathVariable("project-id") String projectId){
        return ResponseEntity.ok (userService.findUsersByProjects(projectId));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
