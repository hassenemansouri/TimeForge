package tn.esprit.user_strategicpartership.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.user_strategicpartership.dto.UpdateUserDto;
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
    @Operation(summary = "Search users by name or email")
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        List<User> users = userService.searchUsers(query);
        return ResponseEntity.ok(users);
    }
    @PostMapping("/{userId}/photo")
    public ResponseEntity<User> uploadUserPhoto(
        @PathVariable String userId,
        @RequestParam("file") MultipartFile file) throws IOException {
        User updatedUser = userService.updateUserPhoto(userId, file);
        return ResponseEntity.ok(updatedUser);
    }

    // Get user photo (returns Base64 string)
    @GetMapping("/{userId}/photo")
    public ResponseEntity<String> getUserPhoto(@PathVariable String userId) {
        String photoBase64 = userService.getUserPhoto(userId);
        return ResponseEntity.ok(photoBase64);
    }

    // Delete user photo
    @DeleteMapping("/{userId}/photo")
    public ResponseEntity<Void> deleteUserPhoto(@PathVariable String userId) {
        userService.removeUserPhoto(userId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Optional<Object>> updateUser(
        @PathVariable String userId,
        @RequestBody UpdateUserDto updateUserDto
    ) {
        Optional<Object> updatedUser = Optional.ofNullable(
            userService.updateUser(userId, updateUserDto));
        return ResponseEntity.ok(updatedUser);
    }
}
