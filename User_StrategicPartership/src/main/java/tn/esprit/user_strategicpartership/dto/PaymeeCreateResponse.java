package tn.esprit.user_strategicpartership.dto;

import lombok.Data;

@Data
public class PaymeeCreateResponse {
  private Boolean status;
  private String message;
  private Integer code;
  private PaymentData data;

  @Data
  public static class PaymentData {
    private String token;
    private String orderId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String note;
    private Double amount;
    private String paymentUrl;
  }
}