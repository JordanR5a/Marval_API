package edu.neumont.rzarkowski.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.neumont.rzarkowski.api.SecurityConfig;
import edu.neumont.rzarkowski.api.model.User;
import edu.neumont.rzarkowski.api.model.UserRepository;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void createUser(@RequestBody User user) throws JsonGenerationException, JsonMappingException, IOException {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		for (String auth : user.getAuthoritiesAsStrings()) {
			SecurityConfig.validateAuthorityExists(auth);			
		}
		userRepo.save(user);
	}
	
	@RequestMapping(path = "/exists", method = RequestMethod.GET)
	public boolean exists() { return true; }
	
	@RequestMapping(path = "/grant", method = RequestMethod.PATCH)
	public void grantAuthority(@RequestBody GrantRequest grantRequest) throws JsonParseException, JsonMappingException, IOException {
		User user = userRepo.findOne(grantRequest.getUsername());
		SecurityConfig.validateAuthorityExists(grantRequest.getAuthority());
		user.getAuthoritiesAsStrings().add(grantRequest.getAuthority());
		userRepo.save(user);
	}
	
	public static class GrantRequest {
		private String username;
		private String authority;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getAuthority() {
			return authority;
		}
		public void setAuthority(String authority) {
			this.authority = authority;
		}
	}
	
//	@RequestMapping(path = "/grant/{username}/{authority}", method = RequestMethod.PATCH)
//	public void grantAuthority(String username, String authority) throws JsonParseException, JsonMappingException, IOException {
//		User user = userRepo.findOne(username);
//		SecurityConfig.validateAuthorityExists(authority);
//		user.getAuthoritiesAsStrings().add(authority);
//		userRepo.save(user);
//	}
	

}
