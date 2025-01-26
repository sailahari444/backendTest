package com.myWebApplication.GlowStudio.dto;

import com.myWebApplication.GlowStudio.enums.UserRole;

import lombok.Data;

@Data
public class Userdto {

	private Long id;
	
	private String email;
	
	private String name;
	
	private UserRole role;

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public UserRole getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
}
