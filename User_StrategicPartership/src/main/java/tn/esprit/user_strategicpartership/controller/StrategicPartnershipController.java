package tn.esprit.user_strategicpartership.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;
import tn.esprit.user_strategicpartership.service.StrategicPartnershipService;

@RestController
@RequestMapping("/api/partnerships")
public class StrategicPartnershipController {
  private final StrategicPartnershipService partnershipService;

  public StrategicPartnershipController(StrategicPartnershipService partnershipService) {
    this.partnershipService = partnershipService;
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
}