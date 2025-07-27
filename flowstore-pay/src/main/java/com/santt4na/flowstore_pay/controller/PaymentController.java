package com.santt4na.flowstore_pay.controller;

import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.flowstore_pay.dto.PaymentRequestDTO;
import com.santt4na.flowstore_pay.dto.PaymentResponseDTO;
import com.santt4na.flowstore_pay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
		PaymentResponseDTO response = service.createPayment(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/process")
	public void processPayment(@RequestBody OrderDTO dto) {
		service.processPayment(dto);
	}
	
	@GetMapping()
	public ResponseEntity<List<PaymentResponseDTO>> listAll() {
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponseDTO> getById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.getPaymentById(id));
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<PaymentResponseDTO> getByOrderId(@PathVariable String orderId) {
		return ResponseEntity.ok(service.getPaymentByOrderId(orderId));
	}
}