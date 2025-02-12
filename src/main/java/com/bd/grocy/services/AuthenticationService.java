package com.bd.grocy.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bd.grocy.dtos.LoginUserDto;
import com.bd.grocy.dtos.RegisterUserDto;
import com.bd.grocy.models.User;
import com.bd.grocy.repository.UserRepository;

@Service
public class AuthenticationService {
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationService(
			UserRepository userRepository,
			AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder
	) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User signup(RegisterUserDto input) {
		User user = new User();
		
		user.setFullName(input.getFullName());
		user.setEmail(input.getEmail());
		user.setUsername(input.getUserName());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		
		return userRepository.save(user);
	}
	
	public User authenticate(LoginUserDto input) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						input.getEmail(),
						input.getPassword()
						)
		);
		
		return userRepository.findByEmail(input.getEmail())
				.orElseThrow();
	}
}