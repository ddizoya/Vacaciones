package net.vacaciones.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.vacaciones.dto.UserDTO;
import net.vacaciones.entity.Users;
import net.vacaciones.repository.UserRepository;
import net.vacaciones.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public ModelMapper modelMapper;

	private Logger logger = Logger.getLogger(UserController.class);

	@GetMapping("/register")
	public ResponseEntity<String> register(HttpServletResponse response, @ModelAttribute UserDTO userDTO) {

		logger.info(userDTO);

		if (userDTO.getEmail() == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN)
					.body("Email is not set!");
		}

		Users user = modelMapper.map(userDTO, Users.class);
		
		try {
			
			if (userRepository.findByName(user.getName()) != null) {
				logger.info(String.format("User already exits: %s", user.toString()));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN)
						.body("User already exists!");
			}
			
			if (userRepository.save(user) == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.TEXT_PLAIN)
						.body("User cannot be created!");
			}

		} catch (Exception e) {

		}

		return ResponseEntity.status(HttpStatus.OK).build();

	}

}
