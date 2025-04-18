package tn.esprit.user_strategicpartership.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "blockchain_records")
@Getter
@Setter
public class BlockchainRecord {
  @Id
  private String hash;
  private String previousHash;
  private LocalDateTime timestamp;
  private String dataHash;
  private String nonce;
  private String partnershipId; // Reference to StrategicPartnership

  // Constructors
  public BlockchainRecord() {}

  public BlockchainRecord(String hash, String previousHash, LocalDateTime timestamp,
      String dataHash, String nonce, String partnershipId) {
    this.hash = hash;
    this.previousHash = previousHash;
    this.timestamp = timestamp;
    this.dataHash = dataHash;
    this.nonce = nonce;
    this.partnershipId = partnershipId;
  }

  // Getters and Setters
  // ... (generate these in your IDE)
}
