package com.santt4na.flowstore_order.security;

import com.santt4na.config.CommonSecurityConfig;
import com.santt4na.security.FilterCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Import(CommonSecurityConfig.class)
public class LocalSecurityConfig {
	@Autowired
	private FilterCommon filterCommon;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().authenticated())
			.addFilterBefore(filterCommon, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}