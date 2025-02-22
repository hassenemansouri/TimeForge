package tn.esprit.goal_reward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GoalRewardApplication {

    public static void main(String[] args) {
        SpringApplication.run ( GoalRewardApplication.class, args );
    }

}
