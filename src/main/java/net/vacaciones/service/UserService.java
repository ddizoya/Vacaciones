package net.vacaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.vacaciones.entity.Users;
import net.vacaciones.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Users user = userRepository.findByName(name);
		if (user == null)
			return null;
		return new User(user.getName(), user.getPassword(), null);

	}

}
