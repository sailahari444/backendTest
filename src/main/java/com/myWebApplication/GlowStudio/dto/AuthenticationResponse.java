package com.myWebApplication.GlowStudio.dto;

import com.myWebApplication.GlowStudio.enums.UserRole;

public class AuthenticationResponse {

	private String jwt;
	
	private Long userId;
	
	private UserRole role;

	public String getJwt() {
		return jwt;
	}

	public Long getUserId() {
		return userId;
	}

	public UserRole getRole() {
		return role;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
}
