package tn.esprit.notification_message_collaboration;

import lombok.Builder;
import lombok.Data;

import tn.esprit.notification_message_collaboration.entity.Message;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class FullCollaborationResponse {
    private String chatTitle;
    private Date createdAt;
    private LocalDateTime lastUpdated;
    private List<Message> messages;

}