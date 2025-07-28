package com.santt4na.flowstore_notification;

import com.santt4na.config.CommonSecurityConfig;
import com.santt4na.kafka.KafkaTopicConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({KafkaTopicConfig.class, CommonSecurityConfig.class})
public class FlowstoreNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowstoreNotificationApplication.class, args);
	}

}
