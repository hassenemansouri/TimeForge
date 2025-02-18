package tn.esprit.user;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public User createUser(User user);
    public List<User> getAllUsers();
    public Optional<User> getUserById(String id);
    public User updateUser(String id, User updatedUser);
    public void deleteUser(String id);
}
