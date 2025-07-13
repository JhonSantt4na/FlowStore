package com.santt4na.flowstore_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FlowstoreOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowstoreOrderApplication.class, args);
	}

}