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

import edu.neumont.rzarkowski.api.model.Vehicle;
import edu.neumont.rzarkowski.api.model.VehicleRepository;

@RestController
@RequestMapping("/vehicle")
public class VehicleRestController {
	
	@Autowired
	private VehicleRepository repo;

	@RequestMapping(path = "", method = RequestMethod.POST)
	public void createVehicle(@RequestBody Vehicle vehicle) throws JsonParseException, JsonMappingException, IOException {
		repo.add(vehicle);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteVehicle(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		repo.delete(id);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
	public void updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicle) throws JsonParseException, JsonMappingException, IOException {
		repo.update(id, vehicle);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<Vehicle> findAllVehicles() throws FileNotFoundException, IOException {
		return repo.findAll().stream().sorted()
				.collect(Collectors.toList());
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Vehicle findVehicleById(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException {
		return repo.findById(id).orElse(null);
	}


}
