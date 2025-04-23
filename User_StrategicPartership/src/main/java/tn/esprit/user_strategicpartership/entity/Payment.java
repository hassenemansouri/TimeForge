package tn.esprit.user_strategicpartership.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "payments")
public class Payment {
  @Id
  private String id;

  @Indexed(unique = true)
  private String orderId;

  @Indexed
  private String paymeeToken;

  private BigDecimal amount;
  private String currency;
  private String status; // PENDING, PAID, FAILED, CANCELLED, EXPIRED
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String note;

  private String transactionId;
  private BigDecimal receivedAmount;
  private BigDecimal fees;

  private LocalDateTime createdAt = LocalDateTime.now();
  private LocalDateTime updatedAt;
  private LocalDateTime expiresAt = LocalDateTime.now().plusHours(24);

  // Payment method details
  private String paymentMethod; // CARD, MOBILE_MONEY, BANK_TRANSFER
  private String paymentProvider; // PAYMEE, etc.
}