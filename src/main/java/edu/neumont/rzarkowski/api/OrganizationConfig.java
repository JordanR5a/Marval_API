package edu.neumont.rzarkowski.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.neumont.rzarkowski.api.model.OrganizationRepository;

@Configuration
public class OrganizationConfig {

	@Bean
	public OrganizationRepository organizationRepository() {
		return new OrganizationRepository();
	}
}
