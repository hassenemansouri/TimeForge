package tn.esprit.user_strategicpartership.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import tn.esprit.user_strategicpartership.dto.PaymentCompletedEvent;
import tn.esprit.user_strategicpartership.entity.Payment;

@Component
@RequiredArgsConstructor
public class PaymentEventPublisher {
  private final ApplicationEventPublisher eventPublisher;

  public void publishPaymentCompleted(Payment payment) {
    eventPublisher.publishEvent(new PaymentCompletedEvent(payment));
  }

  public void publishPaymentFailed(Payment payment) {
    eventPublisher.publishEvent(new PaymentFailedEvent(payment));
  }

  public void publishPaymentCancelled(Payment payment) {
    eventPublisher.publishEvent(new PaymentCancelledEvent(payment));
  }

  public void publishPaymentExpired(Payment payment) {
    eventPublisher.publishEvent(new PaymentExpiredEvent(payment));
  }
}