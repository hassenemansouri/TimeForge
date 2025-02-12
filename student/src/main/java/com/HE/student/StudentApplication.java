package com.HE.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.HE.student")
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

}
