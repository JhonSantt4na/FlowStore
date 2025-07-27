package com.santt4na.flowstore_order.events;

import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.flowstore_order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class OrderCreatedListener {
	
	@Autowired
	private OrderService orderService;
	
	@KafkaListener(topics = "stock-failed", groupId = "order-group")
	public void handleStockFail(OrderDTO dto){
		System.out.println("Stock failure for order " + dto.id());
		orderService.cancelOrder(dto.id(), "Insufficient stock.");
	}
	
	@KafkaListener(topics = "payment-failed", groupId = "order-group")
	public void handlePaymentFail(OrderDTO dto){
		System.out.println("Payment failure for order "+ dto.id());
		orderService.cancelOrder(dto.id(), "Denied payment");
	}
}