package tn.esprit.user_strategicpartership.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.repository.UserRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImpl implements IUser {

    private UserRepository userRepository;


    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


}
