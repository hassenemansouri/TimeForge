package tn.esprit.user_strategicpartership.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tn.esprit.user_strategicpartership.entity.BlockchainRecord;

public interface BlockchainRecordRepository extends MongoRepository<BlockchainRecord, String> {
  Optional<BlockchainRecord> findTopByOrderByTimestampDesc();

  boolean existsByDataHash(String dataHash);

  Optional<BlockchainRecord> findByDataHash(String dataHash);

  @Query(value = "{}", sort = "{ 'timestamp' : 1 }")
  List<BlockchainRecord> findAllOrderByTimestampAsc();

  long countByTimestampLessThanEqual(LocalDateTime timestamp);

  boolean existsByDataHashAndPartnershipId(String dataHash, String partnershipId);

  Optional<BlockchainRecord> findByPartnershipId(String partnershipId);
}