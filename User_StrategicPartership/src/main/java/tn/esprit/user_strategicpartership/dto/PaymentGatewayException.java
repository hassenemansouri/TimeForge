package tn.esprit.user_strategicpartership.dto;

public class PaymentGatewayException extends RuntimeException {
  public PaymentGatewayException(String message) {
    super(message);
  }
}