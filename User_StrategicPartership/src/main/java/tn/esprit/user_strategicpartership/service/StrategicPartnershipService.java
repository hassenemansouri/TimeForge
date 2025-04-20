package tn.esprit.user_strategicpartership.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.user_strategicpartership.entity.BlockchainRecord;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;
import tn.esprit.user_strategicpartership.repository.StrategicPartnershipRepository;

@Service
public class StrategicPartnershipService {

  private final StrategicPartnershipRepository partnershipRepository;
  private final BlockchainService blockchainService;


  public  StrategicPartnershipService(StrategicPartnershipRepository partnershipRepository,
      BlockchainService blockchainService) {
    this.partnershipRepository = partnershipRepository;
    this.blockchainService = blockchainService;
  }

  public StrategicPartnertship createPartnership(StrategicPartnertship partnership) {
    if (partnershipRepository.existsByName(partnership.getName())) {
      throw new IllegalArgumentException("Partnership with this name already exists");
    }

    partnership.setCreationDate(LocalDateTime.now());
    StrategicPartnertship savedPartnership = partnershipRepository.save(partnership);

    // Register in blockchain
    BlockchainRecord record = blockchainService.registerInBlockchain(savedPartnership);
    savedPartnership.setBlockchainHash(record.getHash());
    savedPartnership.setBlockchainTimestamp(record.getTimestamp());

    return partnershipRepository.save(savedPartnership);
  }

  public boolean verifyPartnership(String partnershipId) {
    StrategicPartnertship partnership = partnershipRepository.findById(partnershipId)
        .orElseThrow(() -> new IllegalArgumentException("Partnership not found"));
    return blockchainService.verifyPartnership(partnership);
  }


  public List<StrategicPartnertship> getAllPartnerships() {
    return partnershipRepository.findAll();
  }

  public StrategicPartnertship getPartnership(String partnershipId) {
    return partnershipRepository.findById(partnershipId)
        .orElseThrow(() -> new IllegalArgumentException("Partnership not found"));
  }
}
