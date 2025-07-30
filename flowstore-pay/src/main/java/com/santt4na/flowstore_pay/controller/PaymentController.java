package com.santt4na.flowstore_pay.controller;

import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.flowstore_pay.dto.PaymentRequestDTO;
import com.santt4na.flowstore_pay.dto.PaymentResponseDTO;
import com.santt4na.flowstore_pay.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private final PaymentService service;
	
	public PaymentController(PaymentService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO dto) {
		log.error("Payment Created for order ID: '{}'", dto.orderId());
		PaymentResponseDTO response = service.createPayment(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/process")
	public void processPayment(@RequestBody OrderDTO dto) {
		log.info("Initiating payment for order ID: {}", dto.id());
		service.processPayment(dto);
	}
	
	@GetMapping()
	public ResponseEntity<List<PaymentResponseDTO>> listAll() {
		log.warn("List all Payment");
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponseDTO> getById(@PathVariable UUID id) {
		log.info("Fetching payment info with ID: {}", id);
		return ResponseEntity.ok(service.getPaymentById(id));
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<PaymentResponseDTO> getByOrderId(@PathVariable String orderId) {
		log.info("Fetching orderID info with ID: {}", orderId);
		return ResponseEntity.ok(service.getPaymentByOrderId(orderId));
	}
}