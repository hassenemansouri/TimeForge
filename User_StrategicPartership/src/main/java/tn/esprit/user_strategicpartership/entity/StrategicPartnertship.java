package tn.esprit.user_strategicpartership.entity;

import java.util.ArrayList;
import java.util.Date;
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
  private Date endDate;
  private List<String> participants;
  private String blockchainHash;
  private LocalDateTime blockchainTimestamp;


}
