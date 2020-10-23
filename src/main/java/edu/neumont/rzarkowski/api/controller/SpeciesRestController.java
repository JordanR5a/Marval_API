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

import edu.neumont.rzarkowski.api.model.Species;
import edu.neumont.rzarkowski.api.model.SpeciesRepository;

@RestController
@RequestMapping("/species")
public class SpeciesRestController {
	
	@Autowired
	private SpeciesRepository repo;

	@RequestMapping(path = "", method = RequestMethod.POST)
	public void createSpecies(@RequestBody Species species) throws JsonParseException, JsonMappingException, IOException {
		repo.add(species);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteSpecies(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		repo.delete(id);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
	public void updateSpecies(@PathVariable int id, @RequestBody Species species) throws JsonParseException, JsonMappingException, IOException {
		repo.update(id, species);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<Species> findAllSpecies() throws FileNotFoundException, IOException {
		return repo.findAll().stream().sorted()
				.collect(Collectors.toList());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Species findSpeciesById(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		return repo.findById(id).orElse(null);
	}

}
