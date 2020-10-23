package edu.neumont.rzarkowski.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.neumont.rzarkowski.api.model.VehicleRepository;

@Configuration
public class VehicleConfig {
	
	@Bean
	public VehicleRepository vehicleRepository() {
		return new VehicleRepository();
	}

}
