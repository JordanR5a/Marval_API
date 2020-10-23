package edu.neumont.rzarkowski.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.neumont.rzarkowski.api.model.CharacterRepository;

@Configuration
public class CharacterConfig {

	@Bean
	public CharacterRepository characterRepository() {
		return new CharacterRepository();
	}
}
