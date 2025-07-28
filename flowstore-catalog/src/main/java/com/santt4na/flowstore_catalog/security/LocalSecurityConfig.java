package com.santt4na.flowstore_catalog.security;

import com.santt4na.config.CommonSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@Import(CommonSecurityConfig.class)
public class LocalSecurityConfig {
}