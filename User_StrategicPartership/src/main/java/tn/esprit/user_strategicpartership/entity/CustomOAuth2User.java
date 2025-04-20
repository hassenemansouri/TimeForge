package tn.esprit.user_strategicpartership.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomOAuth2User implements OAuth2User {

  private OAuth2User oauth2User;
  @Getter
  private User user;

  public CustomOAuth2User(OAuth2User oauth2User, User user) {
    this.oauth2User = oauth2User;
    this.user = user;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return oauth2User.getAttributes();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getName() {
    return oauth2User.getAttribute("name");
  }

  public String getEmail() {
    return oauth2User.getAttribute("email");
  }

}