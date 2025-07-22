package com.santt4na.config;

import com.santt4na.security.FilterCommon;
import com.santt4na.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonSecurityConfig {
	
	@Bean
	public FilterCommon filterCommon() {
		return new FilterCommon();
	}
	
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
}
