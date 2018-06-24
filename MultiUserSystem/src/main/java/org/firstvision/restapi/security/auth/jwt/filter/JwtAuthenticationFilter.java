package org.firstvision.restapi.security.auth.jwt.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.firstvision.restapi.constants.JwtConstant;
import org.firstvision.restapi.security.auth.jwt.model.JwtTokenDetail;
import org.firstvision.restapi.security.auth.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService  userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader(JwtConstant.HEADER_STRING);
		String username = null;
		String authToken = null;
		
		System.out.println("JwtAuthenticationFilter: "+ header);
		if(header != null && header.startsWith(JwtConstant.TOKEN_PREFIX)) {
			authToken = header.replace(JwtConstant.TOKEN_PREFIX, "");
			
			try {
				JwtTokenDetail jwtToken = jwtTokenUtil.parseToken(authToken);
				username = jwtToken.getUsername();
			} catch (IllegalArgumentException e) {
                logger.error("An error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("The token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
			
		} else {
            logger.warn("Couldn't find bearer string, will ignore the header");
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			System.out.println("After token found in header");
			
			if(jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("Authenticated user " + username + ", etting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		System.out.println("JwtAuthenticationFilter: Next");
		filterChain.doFilter(request, response);
	}

}
