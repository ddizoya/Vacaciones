package net.vacaciones.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.vacaciones.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RESTAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable();
		
		http
		.authorizeRequests()
			.anyRequest().authenticated()
		.and()
	        .formLogin()
		        .loginProcessingUrl("/api/user/log")
		        .passwordParameter("password")
		        .usernameParameter("username")
		        .successHandler(restAuthenticationSuccessHandler)
	        .permitAll();
		
		http.httpBasic().disable();
			
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//	    String currentUserName = authentication.getName();
//	    System.out.println(String.format("User authenticated --> ", currentUserName)); 
	}
	
	
	
	
	
}
