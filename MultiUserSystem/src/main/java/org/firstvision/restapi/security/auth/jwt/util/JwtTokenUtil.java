package org.firstvision.restapi.security.auth.jwt.util;

import java.util.Arrays;
import java.util.Date;

import org.firstvision.restapi.constants.JwtConstant;
import org.firstvision.restapi.security.auth.jwt.model.JwtTokenDetail;
import org.firstvision.restapi.user.UserDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	private JwtTokenDetail jwtToken;
	
	public JwtTokenDetail parseToken(String token) {
		Claims claims = getAllClaimsFromToken(token);
		jwtToken = new JwtTokenDetail();
		
		System.out.println("Parsing now token, claims= "+ claims.toString());
		setCredentials(claims);
		System.out.println("Parsed Token: "+ jwtToken);
		return jwtToken;
	}

	private Claims getAllClaimsFromToken(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(JwtConstant.SIGNING_KEY)
					.parseClaimsJws(token)
					.getBody();
		}catch(JwtException | ClassCastException e) {
			return null;
		}
	}

	private void setCredentials(Claims claims) {
		jwtToken.setUsername(claims.getSubject());
		jwtToken.setExpirationDate(claims.getExpiration());
	}
	
	public String generateToken(UserDTO user) {
		Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

		System.out.println("Generating Token for user: "+ user);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuer("Vision First")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ JwtConstant.TOKEN_VALIDITY_SECONDS))
				.signWith(SignatureAlgorithm.HS256, JwtConstant.SIGNING_KEY)
				.compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = jwtToken.getUsername();
		return (
				userDetails.getUsername().equals(username)
				&& !isTokenExpired(token)
			);
	}

	private boolean isTokenExpired(String token) {
		Date expiration = jwtToken.getExpirationDate();
		return expiration.before(new Date());
	}

}
