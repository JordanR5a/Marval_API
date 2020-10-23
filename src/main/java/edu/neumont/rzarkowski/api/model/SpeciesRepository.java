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

public class SpeciesRepository {
	
private File data = new File("data/species.json");
	
	public void add(Species entry) throws JsonParseException, JsonMappingException, IOException {
		List<Species> species = findAll();
		species.add(entry);
		save(species);
	}
	
	public void delete(int id) throws JsonParseException, JsonMappingException, IOException {
		List<Species> species = findAll();
		species.remove(findIndexById(species, id));
		save(species);
	}
	
	public void save(List<Species> species) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.writeValue(data, species);
	}
	
	public void update(int id, Species updatedSpecies) throws JsonParseException, JsonMappingException, IOException {
		delete(id);
		add(updatedSpecies);
	}
	
	private int findIndexById(List<Species> species, int id) {
		for (int i = 0; i < species.size(); i++) {
			if (species.get(i).getId() == id) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Cannot find item with ID: " + id);
	}
	
	public List<Species> findAll() throws JsonParseException, JsonMappingException, IOException {
		if(!data.exists() || data.length() <= 0) {
			return new ArrayList<Species>();
		}
		ObjectMapper om = new ObjectMapper();
		List<Species> entries = om.readValue(data, new TypeReference<List<Species>>(){});
		return entries;
	}
	
	public Optional<Species> findById(int id) throws JsonParseException, JsonMappingException, IOException {
		return findAll().stream()
				.filter(e -> e.getId() == id)
				.findFirst();
	}

}
