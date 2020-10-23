package edu.neumont.rzarkowski.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.neumont.rzarkowski.api.model.SpeciesRepository;

@Configuration
public class SpeciesConfig {
	
	@Bean
	public SpeciesRepository speciesRepository() {
		return new SpeciesRepository();
	}

}
