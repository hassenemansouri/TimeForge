package tn.esprit.user_strategicpartership.controller;


import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;
import tn.esprit.user_strategicpartership.service.StrategicPartnershipServiceImpl;


@RestController
@RequestMapping("/StrategicPartnership")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class StrategicPartnershipController {
  private StrategicPartnershipServiceImpl strategicPartnershipService;
  @Operation(summary = "Add a new strategic partnership")
  @PostMapping("/add")
  public void addStrategicPartnership(StrategicPartnertship strategicPartnership) {
    strategicPartnershipService.createPartnership(strategicPartnership);
  }
  @GetMapping
  public List<StrategicPartnertship> getAllPartnerships() {
    return strategicPartnershipService.getAllPartnerships();
  }

  @GetMapping("/{id}")
    public Optional<StrategicPartnertship> getPartnershipById(@PathVariable String id) {
    return strategicPartnershipService.getPartnershipById(id);
  }



  @DeleteMapping("/{id}")
  public void deletePartnership(@PathVariable String id) {
    strategicPartnershipService.deletePartnership(id);
  }

}
