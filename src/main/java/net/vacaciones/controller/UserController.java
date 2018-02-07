package net.vacaciones.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import net.vacaciones.dto.UserDTO;
import net.vacaciones.entity.Users;
import net.vacaciones.repository.UserRepository;
import net.vacaciones.service.UserService;

@Controller
public class UserController {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ModelMapper modelMapper;
	

	private Logger logger = Logger.getLogger(UserController.class);
	
	@GetMapping("/register")
	public void register(HttpServletResponse response, @RequestParam UserDTO userDTO) {
		
		logger.debug(userDTO);
		
		Users user = modelMapper.map(userDTO, Users.class);
		if (userRepository.save(user) == null) {
			response.setStatus(500);
		}
		response.setStatus(200);
		
	}
	
}
