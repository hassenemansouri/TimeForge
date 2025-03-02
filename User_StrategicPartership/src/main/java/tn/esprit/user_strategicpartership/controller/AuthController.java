package tn.esprit.user_strategicpartership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.user_strategicpartership.dto.AuthRequest;
import tn.esprit.user_strategicpartership.dto.AuthResponse;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.repository.UserRepository;
import tn.esprit.user_strategicpartership.security.CustomUserDetailsService;
import tn.esprit.user_strategicpartership.security.JwtUtil;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    try {
      // Check if email already exists
      if (userRepository.findByEmail(user.getEmail()).isPresent()) {
        return ResponseEntity.badRequest().body("Email already exists!");
      }

      // Encrypt password before saving
      user.setPassword(passwordEncoder.encode(user.getPassword()));

      // Save user
      userRepository.save(user);
      return ResponseEntity.ok("User registered successfully!");

    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error registering user: " + e.getMessage());
    }
  }


  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    try {
      // 1) Authenticate using the AuthenticationManager
      UsernamePasswordAuthenticationToken authToken =
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

      authenticationManager.authenticate(authToken);

      // 2) Generate JWT token
      String jwtToken = jwtUtil.generateToken(request.getEmail());

      // 3) Return token in response
      return ResponseEntity.ok(new AuthResponse(jwtToken));

    } catch (AuthenticationException ex) {
      return ResponseEntity.status(401).body("Invalid email or password!");
    }
  }
}
