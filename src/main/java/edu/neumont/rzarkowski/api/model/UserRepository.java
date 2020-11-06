package edu.neumont.rzarkowski.api.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserRepository {
	
private File file = new File("data/users.json");
	
	private ObjectMapper om = new ObjectMapper();
	
	public void loadAll(Map<String, User> users) throws JsonParseException, JsonMappingException, IOException {
		if(!file.exists() || file.length() <= 0) {
			return;
		}
		List<User> userList = om.readValue(file, new TypeReference<List<User>>(){});
		for(User user : userList) {
			users.put(user.getUsername(), user);
		}
	}
	
	public void save(User user) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, User> users = new HashMap<>();
		loadAll(users);
		users.put(user.getUsername(), user);
		om.writeValue(file, users.values());
	}
	
	public User findOne(String username) throws JsonParseException, JsonMappingException, IOException {
		Map<String, User> users = new HashMap<>();
		loadAll(users);
		return users.get(username);
	}
	
	public static class UserList extends ArrayList<User> {
		private static final long serialVersionUID = 1L;
	}


}
