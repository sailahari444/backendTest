package com.myWebApplication.GlowStudio.configs;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.myWebApplication.GlowStudio.services.jwt.UserService;
import com.myWebApplication.GlowStudio.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
	
	private final JwtUtil jwtutil;
	
	private final UserService userService;

	public JwtAuthFilter(JwtUtil jwtutil, UserService userService) {
		super();
		this.jwtutil = jwtutil;
		this.userService = userService;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		final String jwt;
		jwt = authHeader.substring(7);
		final String userEmail;
		
		userEmail = jwtutil.extractUserName(jwt);
		
		if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
			if(jwtutil.isTokenValid(jwt, userDetails)) {
				
				SecurityContext securiyContext = SecurityContextHolder.createEmptyContext();
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities()
						);
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				securiyContext.setAuthentication(authToken);
				SecurityContextHolder.setContext(securiyContext);
				
			}
		}
		
		filterChain.doFilter(request, response);
	}
 
	
}
