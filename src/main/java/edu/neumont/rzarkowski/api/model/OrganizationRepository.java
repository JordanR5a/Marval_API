package edu.neumont.rzarkowski.api.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrganizationRepository {
	
private File data = new File("data/organizations.json");
	
	public void add(Organization organization) throws JsonParseException, JsonMappingException, IOException {
		List<Organization> organizations = findAll();
		organizations.add(organization);
		save(organizations);
	}
	
	public void delete(int id) throws JsonParseException, JsonMappingException, IOException {
		List<Organization> organizations = findAll();
		organizations.remove(findIndexById(organizations, id));
		save(organizations);
	}
	
	public void save(List<Organization> organizations) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.writeValue(data, organizations);
	}
	
	public void update(int id, Organization updatedOrganization) throws JsonParseException, JsonMappingException, IOException {
		delete(id);
		add(updatedOrganization);
	}
	
	private int findIndexById(List<Organization> organizations, int id) {
		for (int i = 0; i < organizations.size(); i++) {
			if (organizations.get(i).getId() == id) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Cannot find item with ID: " + id);
	}
	
	public List<Organization> findAll() throws JsonParseException, JsonMappingException, IOException {
		if(!data.exists() || data.length() <= 0) {
			return new ArrayList<Organization>();
		}
		ObjectMapper om = new ObjectMapper();
		List<Organization> entries = om.readValue(data, new TypeReference<List<Organization>>(){});
		return entries;
	}
	
	public Optional<Organization> findById(int id) throws JsonParseException, JsonMappingException, IOException {
		return findAll().stream()
				.filter(e -> e.getId() == id)
				.findFirst();
	}

}
