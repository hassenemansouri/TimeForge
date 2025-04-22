package tn.esprit.user_strategicpartership.controller;

import lombok.RequiredArgsConstructor;
import tn.esprit.user_strategicpartership.entity.TimeLog;
import tn.esprit.user_strategicpartership.entity.User;
import tn.esprit.user_strategicpartership.service.TimeLogService;
import tn.esprit.user_strategicpartership.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/time-logs")
@RequiredArgsConstructor
public class TimeLogController {
  private final TimeLogService timeLogService;
  private final UserService userService;

  @PostMapping("/start")
  public TimeLog startTimer(@RequestParam String userId,
      @RequestParam String activityDescription,
      @RequestParam(required = false) String projectId) {
    User user = userService.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    return timeLogService.startTimer(user, activityDescription, projectId);
  }

  @PostMapping("/stop")
  public TimeLog stopTimer(@RequestParam String userId) {
    User user = userService.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    return timeLogService.stopTimer(user);
  }

  @GetMapping("/user/{userId}")
  public List<TimeLog> getUserTimeLogs(@PathVariable String userId) {
    User user = userService.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    return timeLogService.getUserTimeLogs(user);
  }

  @GetMapping("/user/{userId}/between")
  public List<TimeLog> getUserTimeLogsBetween(
      @PathVariable String userId,
      @RequestParam LocalDateTime start,
      @RequestParam LocalDateTime end) {
    User user = userService.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    return timeLogService.getUserTimeLogsBetweenDates(user, start, end);
  }
}