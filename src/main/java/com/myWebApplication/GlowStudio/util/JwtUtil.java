package com.myWebApplication.GlowStudio.util;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

	private String generateToken(Map<String,Object> extraClaims, UserDetails details) {
		return Jwts.builder().setClaims(extraClaims).setSubject(details.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
				
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
		 return generateToken(claims, userDetails);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}
	
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private Date extractExpirationDate(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpirationDate(token).before(new Date());
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claim = extractAllClaims(token);
		return claimResolver.apply(claim);
	}
	
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode("413F4428472B4B6258655368566D5970337336763979244226452948484D6351");
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
