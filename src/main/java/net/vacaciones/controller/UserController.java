package net.vacaciones.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.vacaciones.dto.UserDTO;
import net.vacaciones.repository.UserRepository;
import net.vacaciones.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public UserService userService;

	@GetMapping("/register")
	public ResponseEntity<String> register(HttpServletResponse response, @ModelAttribute UserDTO userDTO) {

		return userService.registerUser(userDTO);

	}

	@RequestMapping(value = "/log", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> register() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<String>(String.format("Authenticated as %s", name), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/after", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> afterLog() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<String>(String.format("Authenticated as %s", name), HttpStatus.OK);
	}
	
	

}
