package com.myWebApplication.GlowStudio.services.auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myWebApplication.GlowStudio.dto.SignupRqst;
import com.myWebApplication.GlowStudio.dto.Userdto;
import com.myWebApplication.GlowStudio.entity.User;
import com.myWebApplication.GlowStudio.enums.UserRole;
import com.myWebApplication.GlowStudio.repository.UserRepo;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepo userRepo;
	
	public AuthServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
	
	@PostConstruct
	public void createAdminAcct() {
		Optional<User> adminAcct = userRepo.findByRole(UserRole.ADMIN);
		if(adminAcct.isEmpty()) {
			User user = new User();
			user.setEmail("admin@example.com");
			user.setName("Admin");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepo.save(user);
			System.out.println("Admin account created successfully");
		}
		else {
			System.out.println("Admin Account already present");
		}
	}
	
	public Userdto createUser(SignupRqst signupRqst) {
		if(userRepo.findByEmail(signupRqst.getEmail()).isPresent()) {
			throw new EntityExistsException("User already present with "+ signupRqst.getEmail());
		}
		User user = new User();
		user.setName(signupRqst.getName());
		user.setEmail(signupRqst.getEmail());
		user.setRole(UserRole.CUSTOMER);
		user.setPassword(new BCryptPasswordEncoder().encode(signupRqst.getPassword()));
		User createdUser = userRepo.save(user);
		return createdUser.getUserDto();
	}
	
	
}
