package tn.esprit.notification_message_collaboration.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.notification_message_collaboration.entity.User;

import java.util.List;

@FeignClient(name = "user-service" , url = "${application.config.users-url}")
public interface UserClient {

    @GetMapping("/collaboration/{collaboration-id}")
    List<User> fundAllUsersByCollaboration(@PathVariable("collaboration-id") String collaboration_id);

}
