package com.santt4na.flowstore_auth.service;

import com.santt4na.flowstore_auth.entity.User;
import com.santt4na.flowstore_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository UserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.UserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
		
		return new org.springframework.security.core.userdetails.User(
			user.getEmail(),
			user.getPassword(),
			new ArrayList<>()
		);
		
	}
}
