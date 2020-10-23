package edu.neumont.rzarkowski.api.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.neumont.rzarkowski.api.model.Character;
import edu.neumont.rzarkowski.api.model.CharacterRepository;

@RestController
@RequestMapping("/character")
public class CharacterRestController {
	
	@Autowired
	private CharacterRepository repo;

	@RequestMapping(path = "", method = RequestMethod.POST)
	public void createCharacter(@RequestBody Character character) throws JsonParseException, JsonMappingException, IOException {
		repo.add(character);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteCharacter(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		repo.delete(id);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
	public void updateCharacter(@PathVariable int id, @RequestBody Character character) throws JsonParseException, JsonMappingException, IOException {
		repo.update(id, character);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<Character> findAllCharacters() throws FileNotFoundException, IOException {
		return repo.findAll().stream()
				.collect(Collectors.toList());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Character findCharacterById(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		return repo.findById(id).orElse(null);
	}

}
