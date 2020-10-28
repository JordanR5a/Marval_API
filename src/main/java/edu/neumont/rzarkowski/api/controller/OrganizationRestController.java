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

import edu.neumont.rzarkowski.api.model.Organization;
import edu.neumont.rzarkowski.api.model.OrganizationRepository;

@RestController
@RequestMapping("/organization")
public class OrganizationRestController {
	
	@Autowired
	private OrganizationRepository repo;

	@RequestMapping(path = "", method = RequestMethod.POST)
	public void createOrganization(@RequestBody Organization organization) throws JsonParseException, JsonMappingException, IOException {
		repo.add(organization);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteOrganization(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		repo.delete(id);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
	public void updateOrganization(@PathVariable int id, @RequestBody Organization organization) throws JsonParseException, JsonMappingException, IOException {
		repo.update(id, organization);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<Organization> findAllOrganizations() throws FileNotFoundException, IOException {
		return repo.findAll().stream()
				.collect(Collectors.toList());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Organization findOrganizationById(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		return repo.findById(id).orElse(null);
	}


}
