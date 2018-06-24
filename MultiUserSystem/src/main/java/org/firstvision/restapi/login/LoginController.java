package org.firstvision.restapi.login;

import org.firstvision.restapi.security.auth.jwt.model.JwtToken;
import org.firstvision.restapi.security.auth.jwt.util.JwtTokenUtil;
import org.firstvision.restapi.user.UserDTO;
import org.firstvision.restapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserService userService;
	
	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<?> getUserDetail(
			@RequestBody LoginDTO loginUser
			) {
		
		System.out.println("Login Entry: "+ loginUser.getUsername()+ " - "+ loginUser.getPassword());
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );

		System.out.println("Login1: "+ authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("Login2: "+ SecurityContextHolder.getContext());
        final UserDTO user = userService.getByUsername(loginUser.getUsername());
		System.out.println("Login3: "+ user);
        final String token = jwtTokenUtil.generateToken(user);
		System.out.println("Login4: "+ token);
        return ResponseEntity.ok(new JwtToken(token));
	}

}
