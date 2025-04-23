package tn.esprit.user_strategicpartership.dto;

import lombok.Getter;
import tn.esprit.user_strategicpartership.entity.Payment;

@Getter
public class PaymentCompletedEvent {
  private final Payment payment;

  public PaymentCompletedEvent(Payment payment) {
    this.payment = payment;
  }

}