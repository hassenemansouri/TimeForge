package tn.esprit.project_task.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service" , url = "${application.config.users-url}")
public interface UserClient {

    @GetMapping("/project/{project-id}")
    List<User> fundAllUsersByProjects(@PathVariable("project-id") String projet_id);
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") String userId);
    @GetMapping("/user")
    List<User> getAllUsers();
    @PutMapping("/user/{id}")
    void updateUser(@PathVariable("id") String id, @RequestBody User user);

}
