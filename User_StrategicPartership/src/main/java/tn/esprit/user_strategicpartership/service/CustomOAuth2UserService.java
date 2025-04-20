package tn.esprit.user_strategicpartership.service;


import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import tn.esprit.user_strategicpartership.entity.CustomOAuth2User;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    String provider = userRequest.getClientRegistration().getRegistrationId();
    String providerId = oAuth2User.getAttribute("sub") != null ?
        oAuth2User.getAttribute("sub") : Objects.requireNonNull(oAuth2User.getAttribute("id")).toString();
    String email = oAuth2User.getAttribute("email");
    String name = oAuth2User.getAttribute("name");

    // Find or create user
    User user = userRepository.findByEmail(email)
        .orElseGet(() -> {
          User newUser = new User();
          newUser.setEmail(email);
          newUser.setName(name);
          newUser.setProvider(provider);
          newUser.setProviderId(providerId);
          return userRepository.save(newUser);
        });

    return new CustomOAuth2User(oAuth2User, user);
  }
}