package com.santt4na.flowstore_order.controller;

import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.flowstore_order.dto.CreateOrderDTO;
import com.santt4na.flowstore_order.mapper.OrderMapper;
import com.santt4na.flowstore_order.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	public OrderController(OrderMapper orderMapper, OrderService orderService) {
			this.orderService = orderService;
	}
	
	@Autowired
	private final OrderService orderService;
	
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<OrderDTO> create(@Valid @RequestBody CreateOrderDTO orderdto){
		log.info("Creating new order for user ID: {}", orderdto.clientId());
		OrderDTO created = orderService.createOrder(orderdto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping(value = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDTO> update(@PathVariable Long id, @Valid @RequestBody OrderDTO order){
		log.info("Updating order with ID: {}", order.id());
		OrderDTO updated = orderService.updateOrder(id, order);
		return ResponseEntity.ok().body(updated);
	}
	
	@GetMapping(
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDTO>> listAll(){
		log.info("List all Orders");
		List<OrderDTO> listOfOrder = orderService.listAllOrder();
		return ResponseEntity.ok(listOfOrder);
	}
	
	@GetMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
		log.info("Fetching order with ID: {}", id);
		OrderDTO findOrderId = orderService.findByIdOrder(id);
		return ResponseEntity.ok().body(findOrderId);
	}
	
	@DeleteMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		log.info("Cancelling order with ID: {}", id);
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}
	
}