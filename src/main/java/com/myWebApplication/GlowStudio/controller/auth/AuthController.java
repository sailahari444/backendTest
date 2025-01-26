package com.myWebApplication.GlowStudio.controller.auth;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myWebApplication.GlowStudio.dto.AuthenticationRequest;
import com.myWebApplication.GlowStudio.dto.AuthenticationResponse;
import com.myWebApplication.GlowStudio.dto.SignupRqst;
import com.myWebApplication.GlowStudio.dto.Userdto;
import com.myWebApplication.GlowStudio.entity.User;
import com.myWebApplication.GlowStudio.repository.UserRepo;
import com.myWebApplication.GlowStudio.services.auth.AuthService;
import com.myWebApplication.GlowStudio.services.jwt.UserService;
import com.myWebApplication.GlowStudio.util.JwtUtil;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	private final AuthenticationManager authManager;
	
	private final UserRepo userRepo;
	
	private final JwtUtil jwtUtil;
	
	
	private final UserService userService;

	public AuthController(AuthService authService, AuthenticationManager authManager,UserRepo userRepo, JwtUtil jwtUtil, UserService userService) {
	
		this.authService = authService;
		this.authManager = authManager;
		this.userRepo = userRepo;
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> singupUser(@RequestBody SignupRqst signupRqst){
		try {
			Userdto createdUser = authService.createUser(signupRqst);
			return new ResponseEntity<>(createdUser, HttpStatus.OK);
		}
		catch(EntityExistsException entityExistsException) {
			return new ResponseEntity<>("User already exists with ", HttpStatus.NOT_ACCEPTABLE);
			
		}
		catch(Exception e) {
			return new ResponseEntity<>("User not created. Try again later", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public AuthenticationResponse authResponse(@RequestBody AuthenticationRequest authRequest) {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}
		
		final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authRequest.getEmail());
		
		Optional<User> optionalUser = userRepo.findByEmail(userDetails.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		AuthenticationResponse authResponse = new AuthenticationResponse();
		
	     if(optionalUser.isPresent()) {
	    	 authResponse.setJwt(jwt);
	    	 authResponse.setRole(optionalUser.get().getRole());
	    	 authResponse.setUserId(optionalUser.get().getId());
	    	  
	     }
	     return authResponse;
	}
	
	
	

	
}
