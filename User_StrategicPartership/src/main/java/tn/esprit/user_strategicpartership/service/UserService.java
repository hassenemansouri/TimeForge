package tn.esprit.user_strategicpartership.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Base64;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.user_strategicpartership.dto.UpdateUserDto;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.repository.UserRepository;

import java.util.List;

@Service

public class UserService {
    @Autowired
    private  UserRepository userRepository;  // Injection avec @Autowired

    @Autowired
    private JavaMailSender mailSender;

    private final PasswordEncoder passwordEncoder;



    public User addUser(User user) {
      return   userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findUsersByWorkspace(String workspaceId) {
        return userRepository.findAllByWorkspaceId(workspaceId);
    }

    public List<User> findUsersByGoal(String goalId) {
        return userRepository.findAllByGoalId(goalId);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }


    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


    public List<User> findUsersCollab(String collaborationId) {
        return userRepository.findAllByCollaborationId (collaborationId);
    }

    public List<User> findUsersByProjects(String projectId) {
        return userRepository.findAllByProjectId(projectId);
    }
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

//  public boolean sendPasswordResetEmail(String email) {
//      Optional<User> userOpt = userRepository.findByEmail(email);
//      if (userOpt.isEmpty()) {
//          return false; // User not found
//      }
//
//      User user = userOpt.get();
//      String token = UUID.randomUUID().toString(); // Generate a unique token
//      user.setResetToken(token);
//      userRepository.save(user);
//
//      // Send email with reset link
//      String resetLink = "http://localhost:4200" + "/reset-password?token=" + token;
//      sendEmail(user.getEmail(), "Click the link to reset your password: " + resetLink);
//
//      return true;
//  }
    private void sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText(text);
        mailSender.send(message);
    }



        private final BrevoEmailService brevoEmailService;

        public UserService(PasswordEncoder passwordEncoder, BrevoEmailService brevoEmailService) {
          this.passwordEncoder = passwordEncoder;
          this.brevoEmailService = brevoEmailService;
        }

        public void sendForgotPasswordEmail(String userEmail, String resetToken) {
            String subject = "Reset Your Password";
            String resetLink = "https://your-frontend-url/reset-password?token=" + resetToken;

            String htmlContent = "<p>Click the link below to reset your password:</p>"
                + "<a href='" + resetLink + "'>Reset Password</a>";

            brevoEmailService.sendEmail(userEmail, subject, htmlContent);
        }

    public void saveResetToken(String email, String token) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setResetToken(token);
            user.setResetTokenExpiration(LocalDateTime.now().plusMinutes(30)); // Token valid for 30 min
            userRepository.save(user);
        } else {
            throw new RuntimeException("User with email " + email + " not found");
        }
    }

    // Method to validate the token later
    public User validatePasswordResetToken(String token) {
        Optional<User> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getResetTokenExpiration().isAfter(LocalDateTime.now())) {
                return user;
            } else {
                throw new RuntimeException("Token expired");
            }
        } else {
            throw new RuntimeException("Invalid token");
        }
    }

    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
            .orElseThrow(() -> new RuntimeException("Invalid token"));

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null); // Clear token after use
        userRepository.save(user);
    }

    public List<User> searchUsers(String query) {
        return userRepository.findBySearchQuery(query);
    }

    public User updateUserPhoto(String userId, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(
                Objects.requireNonNull(file.getOriginalFilename()));
            String contentType = file.getContentType();
            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

            user.setPhotoBase64(base64Image);
            user.setPhotoContentType(contentType);

            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("File is empty");
        }
    }

    // Get user photo (returns Base64 string)
    public String getUserPhoto(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPhotoBase64();
    }

    // Remove user photo
    public void removeUserPhoto(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPhotoBase64(null);
        user.setPhotoContentType(null);
        userRepository.save(user);
    }


    public User updateUser(String userId, UpdateUserDto updateUserDto) {
        return userRepository.findById(userId)
            .map(user -> {
                if (updateUserDto.getName() != null) {
                    user.setName(updateUserDto.getName());
                }
                if (updateUserDto.getEmail() != null) {
                    user.setEmail(updateUserDto.getEmail());
                }
                if (updateUserDto.getRole() != null) {
                    user.setRole(updateUserDto.getRole());
                }
                if (updateUserDto.getWorkspaceId() != null) {
                    user.setWorkspaceId(updateUserDto.getWorkspaceId());
                }
                if (updateUserDto.getCollaborationId() != null) {
                    user.setCollaborationId(updateUserDto.getCollaborationId());
                }
                if (updateUserDto.getGoalId() != null) {
                    user.setGoalId(updateUserDto.getGoalId());
                }
                if (updateUserDto.getProjectId() != null) {
                    user.setProjectId(updateUserDto.getProjectId());
                }
                return userRepository.save(user);
            })
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
    }

