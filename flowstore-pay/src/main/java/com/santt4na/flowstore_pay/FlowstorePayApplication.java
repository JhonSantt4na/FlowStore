package com.santt4na.flowstore_pay;

import com.santt4na.kafka.KafkaTopicConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@Import(KafkaTopicConfig.class)
public class FlowstorePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowstorePayApplication.class, args);
	}

}
