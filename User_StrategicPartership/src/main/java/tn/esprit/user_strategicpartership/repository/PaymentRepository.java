package tn.esprit.user_strategicpartership.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tn.esprit.user_strategicpartership.entity.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {
  Optional<Payment> findByOrderId(String orderId);
  Optional<Payment> findByPaymeeToken(String token);

  @Query("{ 'status': 'PENDING', 'expiresAt': { $lt: ?0 } }")
  List<Payment> findExpiredPayments(LocalDateTime now);

  @Query("{ 'status': { $in: ['PAID', 'FAILED'] }, 'updatedAt': { $gte: ?0, $lte: ?1 } }")
  List<Payment> findCompletedPaymentsBetween(LocalDateTime start, LocalDateTime end);

  boolean existsByOrderIdAndStatus(String orderId, String status);
}
