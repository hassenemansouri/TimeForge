package tn.esprit.project_task.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service" , url = "${application.config.users-url}")
public interface UserClient {

    @GetMapping("/project/{project-id}")
    List<User> fundAllUsersByProjects(@PathVariable("project-id") String projet_id);

}
