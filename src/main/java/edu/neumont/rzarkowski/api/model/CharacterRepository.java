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


public class CharacterRepository {
	
	private File data = new File("data/characters.json");
	
	public void add(Character character) throws JsonParseException, JsonMappingException, IOException {
		List<Character> characters = findAll();
		characters.add(character);
		save(characters);
	}
	
	public void delete(int id) throws JsonParseException, JsonMappingException, IOException {
		List<Character> characters = findAll();
		characters.remove(findIndexById(characters, id));
		save(characters);
	}
	
	public void save(List<Character> characters) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.writeValue(data, characters);
	}
	
	public void update(int id, Character updatedCharacter) throws JsonParseException, JsonMappingException, IOException {
		delete(id);
		add(updatedCharacter);
	}
	
	private int findIndexById(List<Character> characters, int id) {
		for (int i = 0; i < characters.size(); i++) {
			if (characters.get(i).getId() == id) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Cannot find item with ID: " + id);
	}
	
	public List<Character> findAll() throws JsonParseException, JsonMappingException, IOException {
		if(!data.exists() || data.length() <= 0) {
			return new ArrayList<Character>();
		}
		ObjectMapper om = new ObjectMapper();
		List<Character> entries = om.readValue(data, new TypeReference<List<Character>>(){});
		return entries;
	}
	
	public Optional<Character> findById(int id) throws JsonParseException, JsonMappingException, IOException {
		return findAll().stream()
				.filter(e -> e.getId() == id)
				.findFirst();
	}

}
