package tn.esprit.user_strategicpartership.dto;

public class InvalidPaymentRequestException extends RuntimeException {

  public InvalidPaymentRequestException(String message) {
    super(message);
  }
}
