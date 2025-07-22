package com.santt4na.flowstore_auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.santt4na.flowstore_auth.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.create()
				.withIssuer("flowstore-auth-api")
				.withSubject(user.getEmail())
				.withClaim("userId", user.getId())
//				.withClaim("roles", user.getRoles.stream()
//					.map(Role::getName)
//					.toList())
				.withExpiresAt(this.generateExpirationDate())
				.sign(algorithm);
		} catch (JWTCreationException e) {
			throw new RuntimeException("Error while generating token", e);
		}
	}
	
	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}