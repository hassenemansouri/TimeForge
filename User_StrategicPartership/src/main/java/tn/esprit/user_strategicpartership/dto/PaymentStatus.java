package tn.esprit.user_strategicpartership.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentStatus {
  private String orderId;
  private String status;
  private BigDecimal amount;
  private String currency;
  private String transactionId;
  private LocalDateTime paymentDate;
  private String paymentMethod;
}