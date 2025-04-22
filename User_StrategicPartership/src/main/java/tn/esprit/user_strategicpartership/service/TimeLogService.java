package tn.esprit.user_strategicpartership.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import tn.esprit.user_strategicpartership.entity.TimeLog;
import tn.esprit.user_strategicpartership.entity.User;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import tn.esprit.user_strategicpartership.repository.TimeLogRepository;

@Service
@RequiredArgsConstructor
public class TimeLogService {
  private final TimeLogRepository timeLogRepository;

  public TimeLog startTimer(User user, String activityDescription, String projectId) {
    // Check if user has an active timer
    TimeLog activeLog = timeLogRepository.findByUserAndIsActiveTrue(user);
    if (activeLog != null) {
      throw new IllegalStateException("User already has an active timer");
    }

    TimeLog newLog = new TimeLog();
    newLog.setUser(user);
    newLog.setStartTime(LocalDateTime.now());
    newLog.setActivityDescription(activityDescription);
    newLog.setProjectId(projectId);
    newLog.setIsActive(true);
    newLog.setCreatedAt(LocalDateTime.now());

    return timeLogRepository.save(newLog);
  }

  public TimeLog stopTimer(User user) {
    TimeLog activeLog = timeLogRepository.findByUserAndIsActiveTrue(user);
    if (activeLog == null) {
      throw new IllegalStateException("No active timer found for user");
    }

    activeLog.setEndTime(LocalDateTime.now());
    activeLog.setIsActive(false);
    return timeLogRepository.save(activeLog);
  }

  public List<TimeLog> getUserTimeLogs(User user) {
    return timeLogRepository.findByUser(user);
  }

  public List<TimeLog> getUserTimeLogsBetweenDates(User user, LocalDateTime start, LocalDateTime end) {
    return timeLogRepository.findByUserAndStartTimeBetween(user, start, end);
  }
}