package com.santt4na.flowstore_pay.event;

import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.flowstore_pay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StockSuccessListener {

	@Autowired
	private PaymentService paymentService;
	
	@KafkaListener(topics = "stock-success", groupId = "payment-group")
	public void handleStockSuccess(OrderDTO orderDTO){
		paymentService.processPayment(orderDTO);
	}
}