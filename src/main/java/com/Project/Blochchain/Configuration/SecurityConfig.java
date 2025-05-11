package com.Project.Blochchain.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Lazy
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
			.csrf(csrf -> csrf.disable()) // cross site request forgery is disabled
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/auth/**").permitAll()// Permiting any request for endpoint /auth/*
					.anyRequest().authenticated() // any request will be authenticated
					)
			.sessionManagement(session -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Not to use any http session
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // enable jwtauthfilter before usernamepassworddauthentication filer
		
		return http.build();
		
	}
	
	
}
