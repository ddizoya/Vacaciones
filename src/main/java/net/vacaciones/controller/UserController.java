package net.vacaciones.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.vacaciones.dto.UserDTO;
import net.vacaciones.entity.Users;
import net.vacaciones.repository.UserRepository;
import net.vacaciones.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public UserService userService;

	@GetMapping("/register")
	public ResponseEntity<String> register(HttpServletResponse response, @ModelAttribute UserDTO userDTO) {

		logger.debug(userDTO);
		return userService.registerUser(userDTO);
	}

}
