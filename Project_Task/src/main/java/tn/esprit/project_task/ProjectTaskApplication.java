package tn.esprit.project_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProjectTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run ( ProjectTaskApplication.class, args );
    }

}
