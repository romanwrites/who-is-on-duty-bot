package com.romankukin.projects.whoisondutybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WhoIsOnDutyBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhoIsOnDutyBotApplication.class, args);
	}
}
