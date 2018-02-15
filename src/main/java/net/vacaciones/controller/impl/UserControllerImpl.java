package net.vacaciones.controller.impl;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.vacaciones.dto.UserDTO;
import net.vacaciones.repository.UserRepository;
import net.vacaciones.service.UserService;

@Component("userControllerBasic")
public class UserControllerImpl implements UserControllerBasic {

	private Logger logger = Logger.getLogger(UserControllerImpl.class);

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public UserService userService;

	public ResponseEntity<String> register(HttpServletResponse response, @ModelAttribute UserDTO userDTO) {
		return userService.registerUser(userDTO);
	}

	public ResponseEntity<String> register() {
		String role = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]);
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<String>(String.format("Authenticated as %s with role %s", name, role), HttpStatus.OK);
	}
	
	public ResponseEntity<String> afterLog() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<String>(String.format("Authenticated as %s", name), HttpStatus.OK);
	}

}
