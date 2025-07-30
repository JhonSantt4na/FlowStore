package com.santt4na.flowstore_auth.controller;
import com.santt4na.dtos.Auth.LoginRequestDTO;
import com.santt4na.dtos.Auth.RegisterRequestDTO;
import com.santt4na.flowstore_auth.dto.ResponseDTO;
import com.santt4na.flowstore_auth.entity.User;
import com.santt4na.flowstore_auth.repository.UserRepository;
import com.santt4na.flowstore_auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body){
		log.info("Attempting user login with email: {}", body.email());
		User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
		if(passwordEncoder.matches(body.password(), user.getPassword())) {
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
		}
		log.info("User authenticated successfully");
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body){
		log.info("Attempting user registration: {}", body.email());
		Optional<User> user = this.repository.findByEmail(body.email());
		
		if(user.isEmpty()) {
			User newUser = new User();
			newUser.setPassword(passwordEncoder.encode(body.password()));
			newUser.setEmail(body.email());
			newUser.setName(body.name());
			this.repository.save(newUser);
			
			String token = this.tokenService.generateToken(newUser);
			return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
		}
		log.info("User '{}' registered successfully", body.name());
		return ResponseEntity.badRequest().build();
	}
}