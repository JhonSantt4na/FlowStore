package com.santt4na.flowstore_stock.events;

import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.flowstore_stock.service.InventoryItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreateListener {
	private static final Logger log = LoggerFactory.getLogger(OrderCreateListener.class);
	@Autowired
	private InventoryItemService service;
	
	@KafkaListener(topics = "order-created", groupId = "stock-group")
	public void handleOrderCreated(OrderDTO dto){
		service.processOrderStock(dto);
	}
}