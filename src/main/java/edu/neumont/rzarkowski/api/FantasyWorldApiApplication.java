	package edu.neumont.rzarkowski.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.neumont.rzarkowski.api.model.UserRepository;

@SpringBootApplication
public class FantasyWorldApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasyWorldApiApplication.class, args);
	}
	
	@Bean
	public UserRepository userRepo() {
		return new UserRepository();
	}


}
