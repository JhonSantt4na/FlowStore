package com.santt4na.flowstore_pay.service;

import com.santt4na.flowstore_pay.dto.PaymentRequestDTO;
import com.santt4na.flowstore_pay.dto.PaymentResponseDTO;
import com.santt4na.flowstore_pay.entity.Payment;
import com.santt4na.flowstore_pay.mapper.PaymentMapper;
import com.santt4na.flowstore_pay.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	@Autowired
	private PaymentMapper mapper;
	
	public PaymentResponseDTO processPayment(PaymentRequestDTO dto) {
		Payment payment = mapper.toEntity(dto);
		payment = repository.save(payment);
		return mapper.toResponseDto(payment);
	}
	
	public PaymentResponseDTO getPaymentById(UUID id) {
		Payment payment = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Payment not found"));
		return mapper.toResponseDto(payment);
	}
	
	public PaymentResponseDTO getPaymentByOrderId(String orderId) {
		Payment payment = repository.findByOrderId(orderId)
			.orElseThrow(()-> new RuntimeException("Payment not found"));
		return mapper.toResponseDto(payment);
	}
}
