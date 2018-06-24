package org.firstvision.restapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public UserDTO getUser(
			@PathVariable("userId") int userId
			) {
		return userService.getUser(userId);
	}
	
	@PutMapping()
	public UserDTO updateUser(
			@RequestBody UserDTO user
			) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(
			@PathVariable int userId
			) {
		userService.deleteUser(userId);
	}

}
