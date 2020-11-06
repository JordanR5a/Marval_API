package edu.neumont.rzarkowski.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.neumont.rzarkowski.api.model.User;
import edu.neumont.rzarkowski.api.model.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static final String AUTH_USER = "USER";
	
	public static final String ADMIN_USER = "ADMIN";
	
	private Map<String, User> users = new HashMap<>();
	
	@Autowired
	UserRepository userRepo;
	
	@PostConstruct
	private void initUsers() throws JsonParseException, JsonMappingException, IOException {
		userRepo.loadAll(users);
		users.put("admin", new User("admin", passwordEncoder().encode("adminPassword"), "USER", "ADMIN"));
		users.put("user", new User("user", passwordEncoder().encode("userPassword"), "USER"));
	}
	
	@Bean
	public UserDetailsService UserDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return users.get(username);
			}
		};
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers("/index.html/**").permitAll()
			.antMatchers("/user/**").hasAuthority(ADMIN_USER)
			.antMatchers(HttpMethod.POST).hasAuthority(AUTH_USER)
			.antMatchers(HttpMethod.PATCH).hasAuthority(AUTH_USER)
			.antMatchers(HttpMethod.DELETE).hasAuthority(AUTH_USER)
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().httpBasic();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void validateAuthorityExists(String authority) {
		if (!(authority.equals(ADMIN_USER) || authority.equals(AUTH_USER))) {
			throw new IllegalArgumentException("Authority does not exist: " + authority);
		}
	}
	
}

