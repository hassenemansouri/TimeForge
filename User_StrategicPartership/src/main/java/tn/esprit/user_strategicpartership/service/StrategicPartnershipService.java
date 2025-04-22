package tn.esprit.user_strategicpartership.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  public void deletePartnership(String id) {
    partnershipRepository.deleteById(id);
  }

  public StrategicPartnertship updatePartnership(String id, StrategicPartnertship partnership) {
    StrategicPartnertship existingPartnership = partnershipRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Partnership not found"));

    existingPartnership.setName(partnership.getName());
    existingPartnership.setDescription(partnership.getDescription());
    existingPartnership.setParticipants(partnership.getParticipants());

    return partnershipRepository.save(existingPartnership);
  }

  public Map<String, Object> getDashboardStats() {
    List<StrategicPartnertship> partnerships = partnershipRepository.findAll();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

    // Statistiques mensuelles : nombre de créations de partenariats par mois
    Map<String, Long> partnershipsPerMonth = partnerships.stream()
            .collect(Collectors.groupingBy(
                    p -> p.getCreationDate().format(formatter),
                    Collectors.counting()
            ));

    // Moyenne des participants par mois
    Map<String, Double> avgParticipantsPerMonth = partnerships.stream()
            .collect(Collectors.groupingBy(
                    p -> p.getCreationDate().format(formatter),
                    Collectors.averagingInt(p -> p.getParticipants() != null ? p.getParticipants().size() : 0)
            ));

    // Répartition des partenariats par taille de participants
    Map<String, Long> partnershipsBySize = partnerships.stream()
            .collect(Collectors.groupingBy(
                    p -> {
                      int size = p.getParticipants() != null ? p.getParticipants().size() : 0;
                      if (size <= 3) return "Petit (<=3)";
                      else if (size <= 6) return "Moyen (4-6)";
                      else return "Grand (>6)";
                    },
                    Collectors.counting()
            ));

    // Compilation des statistiques
    Map<String, Object> stats = new HashMap<>();
    stats.put("partnershipsPerMonth", partnershipsPerMonth);
    stats.put("avgParticipantsPerMonth", avgParticipantsPerMonth);
    stats.put("partnershipsBySize", partnershipsBySize);
    stats.put("totalPartnerships", partnerships.size());

    return stats;
  }

}
