package com.santt4na.config;

import com.santt4na.dtos.Order.OrderDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;

@Configuration
@EnableKafka
public class CommonKafkaConfig {
	
	@Bean
	public ProducerFactory<String, OrderDTO> producerFactory() {
		var props = new HashMap<String, Object>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,   "localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,   StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(JsonSerializer.TYPE_MAPPINGS, "orderDTO:com.santt4na.dtos.Order.OrderDTO");
		props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS,     false);
		return new DefaultKafkaProducerFactory<>(props);
	}
	
	@Bean
	public KafkaTemplate<String, OrderDTO> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	@Bean
	public ConsumerFactory<String, OrderDTO> consumerFactory() {
		var props = new HashMap<String, Object>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,   "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG,            "default-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,   StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.santt4na.dtos.Order.OrderDTO");
		return new DefaultKafkaConsumerFactory<>(props,
			new StringDeserializer(),
			new JsonDeserializer<>(OrderDTO.class, false));
	}
	
	@Bean(name = "kafkaListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, OrderDTO> kafkaListenerContainerFactory() {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, OrderDTO>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}