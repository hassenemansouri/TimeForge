package tn.esprit.user_strategicpartership.controller;

import org.springframework.context.ApplicationEvent;
import tn.esprit.user_strategicpartership.entity.Payment;

public class PaymentFailedEvent extends ApplicationEvent {

  public PaymentFailedEvent(Payment payment) {
    super(payment);
  }
}
