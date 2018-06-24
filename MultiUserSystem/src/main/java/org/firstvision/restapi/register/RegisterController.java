package org.firstvision.restapi.register;

import org.firstvision.restapi.user.UserDTO;
import org.firstvision.restapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public UserDTO addUser(
		@RequestBody UserDTO user
			) {
		return userService.addUser(user);
	}

}
