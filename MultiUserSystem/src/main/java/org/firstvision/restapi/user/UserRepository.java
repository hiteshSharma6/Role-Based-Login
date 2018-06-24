package org.firstvision.restapi.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDTO, Integer> {
	
	UserDTO findByUsernameAndPassword(String username, String password);
	UserDTO findByUsername(String username);

}
