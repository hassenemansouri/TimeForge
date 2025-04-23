package tn.esprit.user_strategicpartership.dto;

public class IllegalPaymentStateException extends RuntimeException {
  public IllegalPaymentStateException(String message) {
    super(message);
  }
}