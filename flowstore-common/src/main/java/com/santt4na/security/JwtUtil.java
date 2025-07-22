package com.santt4na.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public boolean isValid(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWT.require(algorithm)
				.withIssuer("flowstore-auth-api")
				.build()
				.verify(token);
			return true;
		} catch (JWTVerificationException e) {
			return false;
		}
	}
	
	public String getSubject(String token) {
		try {
			var subject = decodeToken(token).getSubject();
			if (subject == null) {
				throw new JWTVerificationException("Token sem subject válido");
			}
			return subject;
		} catch (JWTVerificationException e) {
			throw new RuntimeException("Erro ao extrair subject do token");
		}
	}
	
//	public List<String> extractRoles(String token) {
//		try {
//			return decodeToken(token).getClaim("roles").asList(String.class);
//		} catch (Exception e) {
//			return List.of(); // sem roles
//		}
//	}
	
	public Long extractUserId(String token) {
		return JWT.decode(token).getClaim("userId").asLong();
	}
	
	private DecodedJWT decodeToken(String token) {
		return JWT.decode(token);
	}
}
