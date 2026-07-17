package com.micro.gym_persona_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GymPersonaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymPersonaApiApplication.class, args);
	}

}
