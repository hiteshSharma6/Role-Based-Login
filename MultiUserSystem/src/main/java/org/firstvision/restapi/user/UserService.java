package org.firstvision.restapi.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO getUser(int userId) {
		UserDTO user = userRepository.findById(userId).get();
		System.out.println(user);
		return user;
	}
	
	public List<UserDTO> getAllUser() {
//		Map<Integer, String> user = new HashMap<>();
		List<UserDTO> userDetails = new ArrayList<>();
		userRepository.findAll().forEach(userDetails::add);
		return userDetails;
	}
	
	public UserDTO addUser(UserDTO user) {
		System.out.println("Adding User");
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		UserDTO userDTO = userRepository.save(user);
		System.out.println(userDTO);
		return userDTO;
	}
	
	public UserDTO updateUser(UserDTO user) {
		return userRepository.save(user);
	}
	
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);
	}

	public UserDTO getByUsernameAndPassword(String username, String password) {
		UserDTO user = userRepository.findByUsernameAndPassword(username, password);
		System.out.println(user);
		return user;
	}
	
	public UserDTO getByUsername(String username) {
		UserDTO user = userRepository.findByUsername(username);
		System.out.println(user);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Findding by username: "+ username);
		UserDTO user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Username");
		}
		System.out.println("USer found: "+ user.toString());
		return new User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		System.out.println("Authority");
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}
