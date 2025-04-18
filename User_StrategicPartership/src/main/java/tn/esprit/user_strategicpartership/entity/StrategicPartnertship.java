package tn.esprit.user_strategicpartership.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "strategic_partnerships")
@Getter
@Setter
public class StrategicPartnertship {
  @Id
  private String id;
  private String name;
  private String description;
  private LocalDateTime creationDate;
  private List<String> participants;
  private String blockchainHash;
  private LocalDateTime blockchainTimestamp;

  // Constructors, Getters, Setters
  // ... (generate these in your IDE)
}
