package com.myWebApplication.GlowStudio.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetails;

import com.myWebApplication.GlowStudio.dto.Userdto;
import com.myWebApplication.GlowStudio.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	
	private String password;
	
	
	
	private UserRole role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	

	@Override
	public String getUsername() {
		
		return email;
	}



	@Override
	public String getPassword() {
		
		return password;
	}

	 @Override
	    public boolean isAccountNonExpired() {
	        return true; // Always true unless you implement logic to expire accounts
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true; // Always true unless you implement logic for locking accounts
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true; // Always true unless you implement credential expiration logic
	    }

	    @Override
	    public boolean isEnabled() {
	        return true; // Return true if the account is enabled
	    }



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



		public void setPassword(String password) {
			this.password = password;
		}



		public void setName(String name) {
			this.name = name;
		}



		public void setRole(UserRole role) {
			this.role = role;
		}



		public Userdto getUserDto() {
			Userdto dto = new Userdto();
			dto.setId(id);
			dto.setName(name);
			dto.setEmail(email);
			dto.setRole(role);
			
			
			return dto;
		}



		

	
	
	
}
