package com.santt4na.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic orderCreated(){
		return TopicBuilder
			.name("order-created")
			.partitions(1)
			.replicas(1)
			.build();
	}
	
	@Bean
	public NewTopic orderCreatedDLQ() {
		return TopicBuilder
			.name("order-created-dlq")
			.partitions(1)
			.replicas(1)
			.build();
	}
	
	@Bean
	public NewTopic orderCancelled() {
		return TopicBuilder
			.name("order-cancelled")
			.partitions(1)
			.replicas(1)
			.build();
	}
	
	@Bean
	public NewTopic paymentProcessed(){
		return TopicBuilder
			.name("payment-processed")
			.partitions(1)
			.replicas(1)
			.build();
	}
	
	@Bean
	public NewTopic paymentFailed(){
		return TopicBuilder
			.name("payment-failed")
			.partitions(1)
			.replicas(1)
			.build();
	}
	
	@Bean
	public NewTopic paymentProcessedDLQ() {
		return TopicBuilder
			.name("payment-processed-dql")
			.partitions(1)
			.replicas(1)
			.build();
	}
	
	@Bean
	public NewTopic stockFailed() {
		return TopicBuilder
			.name("stock-failed")
			.partitions(1)
			.replicas(1)
			.build();
	}
	
	@Bean
	public NewTopic notificationRequest() {
		return TopicBuilder
			.name("notification-request")
			.partitions(1)
			.replicas(1)
			.build();
	}
}