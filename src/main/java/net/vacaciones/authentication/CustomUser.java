package net.vacaciones.authentication;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.vacaciones.repository.UserRepository;

public class CustomUser extends User implements UserDetails {


	@Autowired
	private UserRepository userRepository;

	public CustomUser(String name, String password) {
		super.setName(name);
		super.setPassword(password);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List <GrantedAuthority> list = getRole().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
