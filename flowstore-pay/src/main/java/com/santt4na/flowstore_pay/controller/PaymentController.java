package com.santt4na.flowstore_pay.controller;

import com.santt4na.flowstore_pay.dto.PaymentRequestDTO;
import com.santt4na.flowstore_pay.dto.PaymentResponseDTO;
import com.santt4na.flowstore_pay.repository.PaymentRepository;
import com.santt4na.flowstore_pay.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class PaymentController {
	
	@Autowired
	private final PaymentService service;
	
	@PostMapping
	public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO dto) {
		PaymentResponseDTO response = service.processPayment(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
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