package tn.esprit.user_strategicpartership.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tn.esprit.user_strategicpartership.repository.UserRepository;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserRepository userRepository;

  public SecurityConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService(userRepository);
  }

  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return new ProviderManager(List.of(authProvider));
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(withDefaults())  // Enable CORS with default configuration
        .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for simplicity (can be enabled later)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/register", "/auth/login").permitAll()  // Allow unauthenticated access to these endpoints
            .anyRequest().authenticated()  // Require authentication for all other requests
        );

    return http.build();
  }
}
