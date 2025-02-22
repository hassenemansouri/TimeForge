package tn.esprit.user_strategicpartership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements IUser {

    @Autowired
    private UserRepository userRepository;  // Injection avec @Autowired

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
