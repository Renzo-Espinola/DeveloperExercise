package com.spacex.trelloassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
public class TrelloAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrelloAssistantApplication.class, args);
	}



}
