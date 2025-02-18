package tn.esprit.notification_message_collaboration.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private String id;
    private String message ;
    private LocalDateTime timestamp  ;
    private boolean isRead ;
    private String relatedEntityId  ;
    @DBRef
    private User recipient;  // Destinataire de la notification
    @DBRef
    private User sender;  // Expéditeur de la notification



}
