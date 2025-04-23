package tn.esprit.user_strategicpartership.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import tn.esprit.user_strategicpartership.config.PaymeeConfig;
import tn.esprit.user_strategicpartership.controller.PaymentEventPublisher;
import tn.esprit.user_strategicpartership.dto.DuplicatePaymentException;
import tn.esprit.user_strategicpartership.dto.IllegalPaymentStateException;
import tn.esprit.user_strategicpartership.dto.InvalidPaymentRequestException;
import tn.esprit.user_strategicpartership.dto.PaymeeCreateResponse;
import tn.esprit.user_strategicpartership.dto.PaymeeWebhookRequest;
import tn.esprit.user_strategicpartership.dto.PaymentGatewayException;
import tn.esprit.user_strategicpartership.dto.PaymentNotFoundException;
import tn.esprit.user_strategicpartership.dto.PaymentRequest;
import tn.esprit.user_strategicpartership.dto.PaymentResponse;
import tn.esprit.user_strategicpartership.dto.PaymentStatus;
import tn.esprit.user_strategicpartership.entity.Payment;
import tn.esprit.user_strategicpartership.repository.PaymentRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymeePaymentService {
  private final PaymeeConfig paymeeConfig;
  private final WebClient webClient;
  private final PaymentRepository paymentRepository;
  private final PaymentEventPublisher eventPublisher;

  @Transactional
  public PaymentResponse initiatePayment(PaymentRequest request) {
    validatePaymentRequest(request);

    Payment payment = createPaymentRecord(request);

    PaymeeCreateResponse paymeeResponse = callPaymeeApi(payment);

    updatePaymentWithPaymeeResponse(payment, paymeeResponse);

    return buildPaymentResponse(payment);
  }

  @Transactional
  public void handleWebhook(PaymeeWebhookRequest webhookRequest) {
    validateWebhookRequest(webhookRequest);

    Payment payment = findPaymentByToken(webhookRequest.getToken());

    updatePaymentStatus(payment, webhookRequest);

    publishPaymentEvent(payment);

    logPaymentCompletion(payment);
  }

  @Transactional(readOnly = true)
  public PaymentStatus getPaymentStatus(String orderId) {
    return paymentRepository.findByOrderId(orderId)
        .map(this::convertToStatusDto)
        .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
  }

  @Transactional
  public void cancelPayment(String orderId) {
    Payment payment = paymentRepository.findByOrderId(orderId)
        .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));

    if (!"PENDING".equals(payment.getStatus())) {
      throw new IllegalPaymentStateException("Only pending payments can be cancelled");
    }

    payment.setStatus("CANCELLED");
    payment.setUpdatedAt(LocalDateTime.now());
    paymentRepository.save(payment);

    eventPublisher.publishPaymentCancelled(payment);
  }

  @Scheduled(fixedRate = 300000) // Every 5 minutes
  @Transactional
  public void processExpiredPayments() {
    LocalDateTime now = LocalDateTime.now();
    List<Payment> expiredPayments = paymentRepository.findExpiredPayments(now);

    expiredPayments.forEach(payment -> {
      payment.setStatus("EXPIRED");
      payment.setUpdatedAt(now);
      paymentRepository.save(payment);
      eventPublisher.publishPaymentExpired(payment);
    });

    if (!expiredPayments.isEmpty()) {
      log.info("Marked {} payments as expired", expiredPayments.size());
    }
  }

  // Helper methods
  private void validatePaymentRequest(PaymentRequest request) {
    if (request.getAmount().compareTo(BigDecimal.ONE) < 0) {
      throw new InvalidPaymentRequestException("Amount must be at least 1");
    }

    if (paymentRepository.existsByOrderIdAndStatus(request.getOrderId(), "PENDING")) {
      throw new DuplicatePaymentException("A pending payment already exists for this order");
    }
  }

  private Payment createPaymentRecord(PaymentRequest request) {
    Payment payment = new Payment();
    payment.setOrderId(request.getOrderId());
    payment.setAmount(request.getAmount());
    payment.setCurrency(request.getCurrency());
    payment.setStatus("PENDING");
    payment.setFirstName(request.getFirstName());
    payment.setLastName(request.getLastName());
    payment.setEmail(request.getEmail());
    payment.setPhone(request.getPhone());
    payment.setNote(request.getNote());
    return paymentRepository.save(payment);
  }

  private PaymeeCreateResponse callPaymeeApi(Payment payment) {
    try {
      return webClient.post()
          .uri("/payments/create")
          .header("Authorization", "Token " + paymeeConfig.getApi().getToken())
          .bodyValue(buildPaymeeRequest(payment))
          .retrieve()
          .bodyToMono(PaymeeCreateResponse.class)
          .block();
    } catch (WebClientResponseException e) {
      log.error("Paymee API error: {}", e.getResponseBodyAsString());
      throw new PaymentGatewayException("Failed to initiate payment with Paymee");
    }
  }

  private Map<String, Object> buildPaymeeRequest(Payment payment) {
    return Map.of(
        "amount", payment.getAmount(),
        "note", payment.getNote(),
        "first_name", payment.getFirstName(),
        "last_name", payment.getLastName(),
        "email", payment.getEmail(),
        "phone", payment.getPhone(),
        "return_url", paymeeConfig.getUrls().getReturnUrl(),
        "cancel_url", paymeeConfig.getUrls().getCancelUrl(),
        "webhook_url", paymeeConfig.getUrls().getWebhookUrl(),
        "order_id", payment.getOrderId()
    );
  }

  private void updatePaymentWithPaymeeResponse(Payment payment, PaymeeCreateResponse response) {
    payment.setPaymeeToken(response.getData().getToken());
    paymentRepository.save(payment);
  }

  private PaymentResponse buildPaymentResponse(Payment payment) {
    return PaymentResponse.builder()
        .paymentUrl(payment.getPaymeeToken()) // Frontend will construct full URL
        .orderId(payment.getOrderId())
        .amount(payment.getAmount())
        .currency(payment.getCurrency())
        .expiresAt(payment.getExpiresAt())
        .build();
  }

  private void validateWebhookRequest(PaymeeWebhookRequest request) {
    String expectedChecksum = DigestUtils.md5Hex(
        request.getToken() +
            (request.getPaymentStatus() ? "1" : "0") +
            paymeeConfig.getApi().getToken()
    );

    if (!expectedChecksum.equals(request.getCheckSum())) {
      throw new SecurityException("Invalid webhook signature");
    }
  }

  private Payment findPaymentByToken(String token) {
    return paymentRepository.findByPaymeeToken(token)
        .orElseThrow(() -> new PaymentNotFoundException("Payment not found for token: " + token));
  }

  private void updatePaymentStatus(Payment payment, PaymeeWebhookRequest request) {
    String newStatus = request.getPaymentStatus() ? "PAID" : "FAILED";
    payment.setStatus(newStatus);
    payment.setUpdatedAt(LocalDateTime.now());

    if (request.getPaymentStatus()) {
      payment.setTransactionId(request.getTransactionId().toString());
      payment.setReceivedAmount(new BigDecimal(String.valueOf(request.getReceivedAmount())));
      payment.setFees(new BigDecimal(String.valueOf(request.getCost())));
      payment.setPaymentMethod(detectPaymentMethod(request));
    }

    paymentRepository.save(payment);
  }

  private String detectPaymentMethod(PaymeeWebhookRequest request) {
    // Logic to detect payment method from webhook data
    if (request.getPhone() != null) return "MOBILE_MONEY";
    return "CARD"; // Default assumption
  }

  private PaymentStatus convertToStatusDto(Payment payment) {
    return PaymentStatus.builder()
        .orderId(payment.getOrderId())
        .status(payment.getStatus())
        .amount(payment.getAmount())
        .currency(payment.getCurrency())
        .transactionId(payment.getTransactionId())
        .paymentDate(payment.getUpdatedAt())
        .paymentMethod(payment.getPaymentMethod())
        .build();
  }

  private void publishPaymentEvent(Payment payment) {
    if ("PAID".equals(payment.getStatus())) {
      eventPublisher.publishPaymentCompleted(payment);
    } else if ("FAILED".equals(payment.getStatus())) {
      eventPublisher.publishPaymentFailed(payment);
    }
  }

  private void logPaymentCompletion(Payment payment) {
    log.info("Payment {} for order {} is now {}",
        payment.getId(), payment.getOrderId(), payment.getStatus());
  }
}