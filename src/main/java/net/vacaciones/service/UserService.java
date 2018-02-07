package net.vacaciones.service;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.vacaciones.dto.UserDTO;
import net.vacaciones.entity.Users;
import net.vacaciones.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Users user = userRepository.findByName(name);
		if (user == null)
			return null;
		return new User(user.getName(), user.getPassword(), null);

	}

	public ResponseEntity<String> registerUser(UserDTO userDTO) {

		String password = userDTO.getPassword();
		userDTO.setPassword(passwordEncoder.encode(password));
		
		Users user = getUserEntity(userDTO, modelMapper);
		
		try {

			if (user.getEmail() == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN)
						.body("Email is not set!");
			} else if (userRepository.findByName(user.getName()) != null) {

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN)
						.body("User already exists!");
			} else if (userRepository.save(user) == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN)
						.body("User cannot be created!");
			}

		} catch (Exception e) {
			logger.warn(e);
		}

		return ResponseEntity.status(HttpStatus.OK).build();

	}
	
	private Users getUserEntity(UserDTO userDTO, ModelMapper modelMapper) {
		return modelMapper.map(userDTO, Users.class);
	}

}
