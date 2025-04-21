package tn.esprit.user_strategicpartership.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.repository.UserRepository;
import tn.esprit.user_strategicpartership.service.StrategicPartnershipService;
@PreAuthorize("hasRole('MANAGER')")
@RestController
@RequestMapping("/api/partnerships")
public class StrategicPartnershipController {
  private final StrategicPartnershipService partnershipService;
  private final UserRepository userRepository;

  public StrategicPartnershipController(StrategicPartnershipService partnershipService,
      UserRepository userRepository) {
    this.partnershipService = partnershipService;
    this.userRepository = userRepository;
  }

  @PostMapping
  public StrategicPartnertship createPartnership(@RequestBody StrategicPartnertship partnership) {
    return partnershipService.createPartnership(partnership);
  }
  @GetMapping("/verify")
  public ResponseEntity<Map<String, Object>> verifyPartnership(
      @RequestParam String id,
      @RequestParam String hash) {

    StrategicPartnertship partnership = partnershipService.getPartnership(id);
    Map<String, Object> response = new HashMap<>();

    if (partnership != null && partnership.getBlockchainHash().equals(hash)) {
      response.put("valid", true);
      response.put("message", "Partnership verified on blockchain");
      response.put("name", partnership.getName());
      response.put("timestamp", partnership.getBlockchainTimestamp());
    } else {
      response.put("valid", false);
      response.put("message", "Verification failed - record not found or hash mismatch");
    }

    return ResponseEntity.ok(response);
  }
  @GetMapping("/{id}/verify")
  public boolean verifyPartnership(@PathVariable String id) {
    return partnershipService.verifyPartnership(id);
  }
  @GetMapping
  public ResponseEntity<List<StrategicPartnertship>> getAllPartnerships() {
    List<StrategicPartnertship> partnerships = partnershipService.getAllPartnerships();
    return ResponseEntity.ok(partnerships);
  }
  @PostMapping("/names")
  public Map<String, String> getNamesByIds(@RequestBody List<String> ids) {
    return userRepository.findByIdIn(ids)
        .stream()
        .collect(Collectors.toMap(
            User::getId,
            User::getName
        ));
  }
  @PostMapping("/names/ids")
  public Map<String, String> getIdsByNames(@RequestBody List<String> names) {
    return userRepository.findByNameIn(names)
        .stream()
        .collect(Collectors.toMap(
            User::getName,
            User::getId
        ));
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePartnership(@PathVariable String id) {
    partnershipService.deletePartnership(id);
    return ResponseEntity.noContent().build();
  }
  @PutMapping("/{id}")
  public ResponseEntity<StrategicPartnertship> updatePartnership(
      @PathVariable String id,
      @RequestBody StrategicPartnertship partnership) {
    StrategicPartnertship updatedPartnership = partnershipService.updatePartnership(id, partnership);
    return ResponseEntity.ok(updatedPartnership);
  }
  @GetMapping("/{id}")  // Make sure this exists
  public ResponseEntity<StrategicPartnertship> getPartnershipById(@PathVariable String id) {
    StrategicPartnertship partnership = partnershipService.getPartnership(id);
    return partnership != null
        ? ResponseEntity.ok(partnership)
        : ResponseEntity.notFound().build();
  }
}