package com.santt4na.flowstore_order.events;

import com.santt4na.dtos.Order.OrderDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedProducer {
	
	private final KafkaTemplate<String, OrderDTO> kafkaTemplate;
	
	public OrderCreatedProducer(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendOrderCreatedEvent(OrderDTO orderDTO){
		kafkaTemplate.send("order-created", orderDTO);
	}
}