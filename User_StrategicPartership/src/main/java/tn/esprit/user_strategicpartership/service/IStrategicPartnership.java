package tn.esprit.user_strategicpartership.service;

import java.util.List;
import java.util.Optional;
import tn.esprit.user_strategicpartership.entity.StrategicPartnertship;

public interface IStrategicPartnership {
  public StrategicPartnertship createPartnership(StrategicPartnertship partnership);
  public List<StrategicPartnertship> getAllPartnerships();
  public Optional<StrategicPartnertship> getPartnershipById(String id);

  public void deletePartnership(String id);

}
