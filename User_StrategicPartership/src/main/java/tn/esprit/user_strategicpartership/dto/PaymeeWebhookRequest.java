package tn.esprit.user_strategicpartership.dto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymeeWebhookRequest {
  private String token;
  private String checkSum;
  private Boolean paymentStatus;
  private String orderId;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String note;
  private BigDecimal amount;
  private Long transactionId;
  private BigDecimal receivedAmount;
  private BigDecimal cost;
}