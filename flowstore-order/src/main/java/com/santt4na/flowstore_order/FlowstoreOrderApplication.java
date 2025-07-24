package com.santt4na.flowstore_order;

import com.santt4na.kafka.KafkaTopicConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
@Import(KafkaTopicConfig.class)
public class FlowstoreOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowstoreOrderApplication.class, args);
	}

}