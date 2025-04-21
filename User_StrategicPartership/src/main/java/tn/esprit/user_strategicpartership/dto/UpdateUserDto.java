package tn.esprit.user_strategicpartership.dto;

import lombok.Data;
import tn.esprit.user_strategicpartership.entity.Role;
@Data
public class UpdateUserDto {
  private String name;
  private String email;
  private String workspaceId;
  private String collaborationId;
  private String goalId;
  private String projectId;
  private Role role;
}
