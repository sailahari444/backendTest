package com.myWebApplication.GlowStudio.dto;

import lombok.Data;

@Data
public class SignupRqst {

	private String name;
	private String email;
	
	private String password;
	
	

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
