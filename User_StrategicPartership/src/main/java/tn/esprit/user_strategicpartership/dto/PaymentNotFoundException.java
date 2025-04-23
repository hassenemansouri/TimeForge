package tn.esprit.user_strategicpartership.dto;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String message) {
        super(message);
    }

}
