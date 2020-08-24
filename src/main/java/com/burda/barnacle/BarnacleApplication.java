package com.burda.barnacle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BarnacleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarnacleApplication.class, args);
	}

}
