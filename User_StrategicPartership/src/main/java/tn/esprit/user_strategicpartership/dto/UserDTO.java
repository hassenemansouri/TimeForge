package tn.esprit.user_strategicpartership.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.user_strategicpartership.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
  private String id;
  private String photoBase64;  // Stores Base64-encoded image
  private String photoContentType;
  private String name;
  private String email;
  private String role;

  public UserDTO(User user) {
    this.id = user.getId();
    this.photoBase64 = user.getPhotoBase64();
    this.photoContentType = user.getPhotoContentType();
    this.name = user.getName();
    this.email = user.getEmail();
    this.role = String.valueOf(user.getRole());
  }
}
