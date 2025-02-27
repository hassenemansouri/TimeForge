package tn.esprit.user_strategicpartership.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "strategicpartnerships")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StrategicPartnertship {
  @Setter
  @Id
  private String id;
  private String partnername;
  private String partnershipType;
  private List<String> Offers;
  @DBRef
  private User user;


}
