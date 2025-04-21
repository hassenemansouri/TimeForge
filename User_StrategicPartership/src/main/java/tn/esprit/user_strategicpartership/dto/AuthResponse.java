package tn.esprit.user_strategicpartership.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.user_strategicpartership.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
  private String token;
  private UserDTO user;


}
