package tn.esprit.notification_message_collaboration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleResourceAccessException(ResourceAccessException ex) {
        // Log l'erreur (tu peux utiliser un logger comme SLF4J)
        System.err.println("Erreur de connexion à Zipkin : " + ex.getMessage());

        // Retourne une réponse appropriée
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Erreur de connexion à Zipkin : " + ex.getMessage());
    }
}