package tn.esprit.user_strategicpartership.controller;

import org.springframework.context.ApplicationEvent;
import tn.esprit.user_strategicpartership.entity.Payment;

public class PaymentExpiredEvent extends ApplicationEvent {

  public PaymentExpiredEvent(Payment payment) {
    super( payment);
  }
}
