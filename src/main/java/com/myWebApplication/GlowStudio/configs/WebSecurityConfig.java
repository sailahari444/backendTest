package com.myWebApplication.GlowStudio.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.myWebApplication.GlowStudio.enums.UserRole;
import com.myWebApplication.GlowStudio.services.jwt.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final UserService userService;
	
	private final JwtAuthFilter jwtAuthFilter;

	public WebSecurityConfig(UserService userService, JwtAuthFilter jwtAuthFilter) {
		
		this.userService = userService;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
				request->request.requestMatchers("/api/auth/**").permitAll()
				.requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
				.requestMatchers("/api/customer/**").hasAnyAuthority(UserRole.CUSTOMER.name())
				.anyRequest().authenticated()).sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		       .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
				
				return http.build();
				
				
		 
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService.userDetailsService());
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authProvider;
	}
}
