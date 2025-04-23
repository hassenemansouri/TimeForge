package tn.esprit.user_strategicpartership.dto;

public class DuplicatePaymentException extends RuntimeException {

  public DuplicatePaymentException(String message) {
    super(message);
  }
}
