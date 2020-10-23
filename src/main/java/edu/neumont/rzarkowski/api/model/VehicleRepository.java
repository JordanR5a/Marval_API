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

public class VehicleRepository {
	
private File data = new File("data/vehicles.json");
	
	public void add(Vehicle vehicle) throws JsonParseException, JsonMappingException, IOException {
		List<Vehicle> vehicles = findAll();
		vehicles.add(vehicle);
		save(vehicles);
	}
	
	public void delete(int id) throws JsonParseException, JsonMappingException, IOException {
		List<Vehicle> vehicles = findAll();
		vehicles.remove(findIndexById(vehicles, id));
		save(vehicles);
	}
	
	public void save(List<Vehicle> vehicles) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.writeValue(data, vehicles);
	}
	
	public void update(int id, Vehicle updatedVehicle) throws JsonParseException, JsonMappingException, IOException {
		delete(id);
		add(updatedVehicle);
	}
	
	private int findIndexById(List<Vehicle> vehicles, int id) {
		for (int i = 0; i < vehicles.size(); i++) {
			if (vehicles.get(i).getId() == id) {
				return i;
			}
		}
		
		throw new IllegalArgumentException("Cannot find item with ID: " + id);
	}
	
	public List<Vehicle> findAll() throws JsonParseException, JsonMappingException, IOException {
		if(!data.exists() || data.length() <= 0) {
			return new ArrayList<Vehicle>();
		}
		ObjectMapper om = new ObjectMapper();
		List<Vehicle> entries = om.readValue(data, new TypeReference<List<Vehicle>>(){});
		return entries;
	}
	
	public Optional<Vehicle> findById(int id) throws JsonParseException, JsonMappingException, IOException {
		return findAll().stream()
				.filter(e -> e.getId() == id)
				.findFirst();
	}

}
