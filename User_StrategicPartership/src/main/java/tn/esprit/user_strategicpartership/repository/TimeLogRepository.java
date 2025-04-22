package tn.esprit.user_strategicpartership.repository;

import tn.esprit.user_strategicpartership.entity.TimeLog;
import tn.esprit.user_strategicpartership.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TimeLogRepository extends MongoRepository<TimeLog, String> {
  List<TimeLog> findByUser(User user);
  List<TimeLog> findByUserAndStartTimeBetween(User user, LocalDateTime start, LocalDateTime end);
  List<TimeLog> findByProjectId(String projectId);
  TimeLog findByUserAndIsActiveTrue(User user);
}