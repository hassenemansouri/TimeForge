package tn.esprit.user_strategicpartership.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tn.esprit.user_strategicpartership.entity.OAuth2LoginSuccessHandler;
import tn.esprit.user_strategicpartership.repository.UserRepository;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;
import tn.esprit.user_strategicpartership.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  // Add these new dependencies
  @Autowired
  private CustomOAuth2UserService customOAuth2UserService;
  @Autowired
  private OAuth2LoginSuccessHandler oauth2LoginSuccessHandler;

  public SecurityConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  @Autowired
  private CorsConfigurationSource corsConfigurationSource;
  private final UserRepository userRepository;


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
  public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfigurationSource))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers(
                "/auth/reset-password",
                "/login",
                "/error",
                "/auth/**",
                "/api/partnerships/**",
                "/api/blockchain/**",
                "/users/**",
                "/swagger-ui/**",
                "/oauth2/**",
                "/login/oauth2/**"
            ).permitAll()
            .anyRequest().authenticated()
        )
//        .oauth2Login(oauth2 -> oauth2
//            .loginPage("/login")  // Custom login page
//            .userInfoEndpoint(userInfo -> userInfo
//                .userService(customOAuth2UserService)
//            )
//            .successHandler(oauth2LoginSuccessHandler)
//        )
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );


    return http.build();
  }





}
