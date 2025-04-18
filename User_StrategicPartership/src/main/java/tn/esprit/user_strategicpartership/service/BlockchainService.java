package tn.esprit.user_strategicpartership.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import tn.esprit.user_strategicpartership.entity.BlockchainRecord;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;
import tn.esprit.user_strategicpartership.repository.BlockchainRecordRepository;

@Service
public class BlockchainService {
  private final BlockchainRecordRepository blockchainRecordRepository;

  public BlockchainService(BlockchainRecordRepository blockchainRecordRepository) {
    this.blockchainRecordRepository = blockchainRecordRepository;
  }

  public BlockchainRecord registerInBlockchain(StrategicPartnertship partnership) {
    String dataHash = calculateDataHash(partnership);

    BlockchainRecord lastRecord = blockchainRecordRepository.findTopByOrderByTimestampDesc()
        .orElse(null);

    String previousHash = lastRecord != null ? lastRecord.getHash() : "0";
    BlockchainRecord newRecord = mineBlock(previousHash, dataHash, partnership.getId());

    return blockchainRecordRepository.save(newRecord);
  }

  public boolean verifyPartnership(StrategicPartnertship partnership) {
    String dataHash = calculateDataHash(partnership);
    return blockchainRecordRepository.existsByDataHashAndPartnershipId(dataHash, partnership.getId());
  }

  public List<BlockchainRecord> getBlockchain() {
    return blockchainRecordRepository.findAllOrderByTimestampAsc();
  }

  private String calculateDataHash(StrategicPartnertship partnership) {
    String data = partnership.getId() + partnership.getName() +
        partnership.getCreationDate() + String.join("", partnership.getParticipants());
    return DigestUtils.sha256Hex(data);
  }

  private BlockchainRecord mineBlock(String previousHash, String dataHash, String partnershipId) {
    long nonce = 0;
    String hash;
    LocalDateTime timestamp = LocalDateTime.now();
    final int difficulty = 4; // Number of leading zeros required

    do {
      String input = previousHash + dataHash + timestamp + nonce;
      hash = DigestUtils.sha256Hex(input);
      nonce++;
    } while (!hash.startsWith("0".repeat(difficulty)));

    return new BlockchainRecord(hash, previousHash, timestamp, dataHash, String.valueOf(nonce), partnershipId);
  }
}