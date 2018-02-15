package net.vacaciones.controller.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.vacaciones.dto.UserDTO;


@RequestMapping("/api/user")
public interface UserControllerBasic {
	
	@GetMapping("/register")
	public abstract ResponseEntity<String> register(HttpServletResponse response, @ModelAttribute UserDTO userDTO);
	
	@RequestMapping(value = "/log", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> register();
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/after", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> afterLog();
	
}
