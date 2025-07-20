package com.santt4na.flowstore_pay.service;

import com.santt4na.flowstore_pay.dto.PaymentRequestDTO;
import com.santt4na.flowstore_pay.dto.PaymentResponseDTO;
import com.santt4na.flowstore_pay.entity.Payment;
import com.santt4na.flowstore_pay.enums.PaymentStatus;
import com.santt4na.flowstore_pay.mapper.PaymentMapper;
import com.santt4na.flowstore_pay.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	@Autowired
	private PaymentMapper mapper;
	
	public PaymentService(PaymentRepository repository, PaymentMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {
		Payment payment = mapper.toEntity(dto);
		payment.setStatus(PaymentStatus.PENDING);
		payment = repository.save(payment);
		return mapper.toResponseDto(payment);
	}
	
	public List<PaymentResponseDTO> listAll() {
		List<Payment> payments = repository.findAll();
		return payments.stream().map(mapper::toResponseDto).toList();
	}
	
	public Boolean processPayment(UUID id, boolean isValid) {
		Payment payment = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Payment not found"));
		
		if (!isValid){
			payment.setStatus(PaymentStatus.REJECTED);
			return false;
		}
		
		payment.setStatus(PaymentStatus.APPROVED);
		repository.save(payment);
		
		return true;
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
