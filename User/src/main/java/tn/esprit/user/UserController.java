package tn.esprit.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService service;

  @PostMapping
  public User createUser(@RequestBody User user) {
    return service.createUser(user);
  }

  @GetMapping
  public List<User> getAllUsers() {
    return service.getAllUsers();
  }

  @GetMapping("/{id}")
  public Optional<User> getUserById(@PathVariable String id) {
    return service.getUserById(id);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable String id, @RequestBody User user) {
    return service.updateUser(id, user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id) {
    service.deleteUser(id);
  }
}
