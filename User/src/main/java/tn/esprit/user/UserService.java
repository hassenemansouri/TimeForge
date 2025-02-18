package tn.esprit.user;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
  @Autowired
  private UserRepository repository;

  public User createUser(User user) {
    return repository.save(user);
  }

  public List<User> getAllUsers() {
    return repository.findAll();
  }

  public Optional<User> getUserById(String id) {
    return repository.findById(id);
  }

  public User updateUser(String id, User updatedUser) {
    if (repository.existsById(id)) {
      updatedUser.setId(id);
      return repository.save(updatedUser);
    }
    return null;
  }

  public void deleteUser(String id) {
    repository.deleteById(id);
  }
}
