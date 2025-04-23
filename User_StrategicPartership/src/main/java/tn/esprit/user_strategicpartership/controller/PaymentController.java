package tn.esprit.user_strategicpartership.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.user_strategicpartership.dto.PaymeeWebhookRequest;
import tn.esprit.user_strategicpartership.dto.PaymentRequest;
import tn.esprit.user_strategicpartership.dto.PaymentResponse;
import tn.esprit.user_strategicpartership.dto.PaymentStatus;
import tn.esprit.user_strategicpartership.service.PaymeePaymentService;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
  private final PaymeePaymentService paymentService;

  @PostMapping
  public ResponseEntity<PaymentResponse> createPayment(
      @Valid @RequestBody PaymentRequest request) {
    return ResponseEntity.ok(paymentService.initiatePayment(request));
  }

  @PostMapping("/webhook")
  public ResponseEntity<Void> handleWebhook(
      @RequestBody PaymeeWebhookRequest request,
      @RequestHeader("X-Paymee-Signature") String signature) {
    paymentService.handleWebhook(request);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<PaymentStatus> getPaymentStatus(
      @PathVariable String orderId) {
    return ResponseEntity.ok(paymentService.getPaymentStatus(orderId));
  }

  @PostMapping("/{orderId}/cancel")
  public ResponseEntity<Void> cancelPayment(
      @PathVariable String orderId) {
    paymentService.cancelPayment(orderId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{orderId}/verify")
  public ResponseEntity<Boolean> verifyPayment(
      @PathVariable String orderId) {
    PaymentStatus status = paymentService.getPaymentStatus(orderId);
    return ResponseEntity.ok("PAID".equals(status.getStatus()));
  }
}