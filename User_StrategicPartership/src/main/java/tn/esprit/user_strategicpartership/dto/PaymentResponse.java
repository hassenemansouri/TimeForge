package tn.esprit.user_strategicpartership.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponse {
  private String paymentUrl;
  private String orderId;
  private BigDecimal amount;
  private String currency;
  private LocalDateTime expiresAt;
}