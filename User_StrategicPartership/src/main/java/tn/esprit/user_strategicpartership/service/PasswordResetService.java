package tn.esprit.user_strategicpartership.service;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

  private final UserRepository userRepository;

  public void generateResetToken(String email) {
    Optional<User> userOptional = userRepository.findByEmail(email);

    if (userOptional.isPresent()) {
      User user = userOptional.get();
      String token = UUID.randomUUID().toString(); // Generate token
      user.setResetToken(token); // Set token
      userRepository.save(user); // Save user with token
    }
  }
}
