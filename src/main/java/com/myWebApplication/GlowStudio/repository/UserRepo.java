package com.myWebApplication.GlowStudio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myWebApplication.GlowStudio.entity.User;
import com.myWebApplication.GlowStudio.enums.UserRole;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  
	Optional<User> findByEmail(String email);
	
	Optional<User> findByRole(UserRole role);
}
