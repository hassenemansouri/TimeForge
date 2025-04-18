package tn.esprit.user_strategicpartership.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Offres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
  @Id
  private String id;
}
