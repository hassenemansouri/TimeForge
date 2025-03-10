package tn.esprit.user_strategicpartership.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
  private String email;
  private String password;
}
