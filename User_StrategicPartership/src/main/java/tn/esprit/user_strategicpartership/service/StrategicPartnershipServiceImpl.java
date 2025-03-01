package tn.esprit.user_strategicpartership.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;
import tn.esprit.user_strategicpartership.repository.StrategicPartnershipRepository;

@Service
@AllArgsConstructor
public class StrategicPartnershipServiceImpl implements IStrategicPartnership{
  private StrategicPartnershipRepository repository;



  public StrategicPartnertship createPartnership(StrategicPartnertship partnership) {
    return repository.save(partnership);
  }


  public List<StrategicPartnertship> getAllPartnerships() {
    return repository.findAll();
  }


  public Optional<StrategicPartnertship> getPartnershipById(String id) {
    return repository.findById(id);
  }





  public void deletePartnership(String id) {
    repository.deleteById(id);
  }
}
