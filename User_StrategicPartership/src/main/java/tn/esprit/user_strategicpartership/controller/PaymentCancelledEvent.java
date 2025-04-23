package tn.esprit.user_strategicpartership.controller;

import org.springframework.context.ApplicationEvent;
import tn.esprit.user_strategicpartership.entity.Payment;

public class PaymentCancelledEvent extends ApplicationEvent {

  public PaymentCancelledEvent(Payment payment) {
    super(payment);
  }
}
