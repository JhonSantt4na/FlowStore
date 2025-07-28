package com.santt4na.flowstore_order.controller;

import com.santt4na.flowstore_order.dto.OrderDTO;
import com.santt4na.flowstore_order.mapper.OrderMapper;
import com.santt4na.flowstore_order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO order){
		OrderDTO created = orderService.createOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping(value = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDTO> update(@PathVariable Long id, @Valid @RequestBody OrderDTO order){
		OrderDTO updated = orderService.updateOrder(id, order);
		return ResponseEntity.ok().body(updated);
	}
	
	@GetMapping(
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDTO>> listAll(){
		List<OrderDTO> listOfOrder = orderService.listAllOrder();
		return ResponseEntity.ok(listOfOrder);
	}
	
	@GetMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
		OrderDTO findOrderId = orderService.findByIdOrder(id);
		return ResponseEntity.ok().body(findOrderId);
	}
	
	@DeleteMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}
	
}