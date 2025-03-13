package tn.esprit.user_strategicpartership.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;
import tn.esprit.user_strategicpartership.service.StrategicPartnershipServiceImpl;

@RestController
@RequestMapping("/api/partnerships")  // Normalisation du chemin
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StrategicPartnershipController {

  private final StrategicPartnershipServiceImpl strategicPartnershipService;

  @Operation(summary = "Add a new strategic partnership")
  @PostMapping("/add")
  public ResponseEntity<StrategicPartnertship> addStrategicPartnership(@RequestBody StrategicPartnertship strategicPartnership) {
    StrategicPartnertship savedPartnership = strategicPartnershipService.createPartnership(strategicPartnership);
    return ResponseEntity.ok(savedPartnership);
  }

  @Operation(summary = "Get all strategic partnerships")
  @GetMapping
  public ResponseEntity<List<StrategicPartnertship>> getAllPartnerships() {
    List<StrategicPartnertship> partnerships = strategicPartnershipService.getAllPartnerships();
    return ResponseEntity.ok(partnerships);
  }

  @Operation(summary = "Get a strategic partnership by ID")
  @GetMapping("/{id}")
  public ResponseEntity<StrategicPartnertship> getPartnershipById(@PathVariable String id) {
    Optional<StrategicPartnertship> partnership = strategicPartnershipService.getPartnershipById(id);
    return partnership.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }



  @Operation(summary = "Delete a strategic partnership by ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePartnership(@PathVariable String id) {
    strategicPartnershipService.deletePartnership(id);
    return ResponseEntity.noContent().build();
  }
}
