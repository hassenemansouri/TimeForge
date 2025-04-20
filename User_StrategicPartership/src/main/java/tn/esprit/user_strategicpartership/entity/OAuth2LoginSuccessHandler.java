package tn.esprit.user_strategicpartership.entity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) throws IOException {

    CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

    // Generate JWT token or create session
    String token = generateTokenForUser(oauthUser.getUser());

    // Redirect with token
    response.sendRedirect("/dashboard?token=" + token);
  }

  private String generateTokenForUser(User user) {
    // Implement your JWT token generation logic here
    return "generated-jwt-token";
  }
}
