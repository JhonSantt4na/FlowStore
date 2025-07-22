package com.santt4na.flowstore_auth;

import com.santt4na.config.CommonSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication()
@Import(CommonSecurityConfig.class)
public class FlowstoreAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowstoreAuthApplication.class, args);
	}

}