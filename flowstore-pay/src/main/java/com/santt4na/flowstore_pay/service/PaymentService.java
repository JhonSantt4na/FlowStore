package com.santt4na.flowstore_pay.service;

import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.flowstore_pay.dto.PaymentRequestDTO;
import com.santt4na.flowstore_pay.dto.PaymentResponseDTO;
import com.santt4na.flowstore_pay.entity.Payment;
import com.santt4na.flowstore_pay.enums.PaymentStatus;
import com.santt4na.flowstore_pay.mapper.PaymentMapper;
import com.santt4na.flowstore_pay.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	@Autowired
	private KafkaTemplate<String, OrderDTO> kafkaTemplate;
	
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
	
	public void processPayment(OrderDTO orderDTO) {
		boolean success = true;
		
		Payment payment = new Payment();
		payment.setOrderId(orderDTO.id().toString());
		payment.setAmount(orderDTO.amount());
		
		if (success){
			payment.setStatus(PaymentStatus.APPROVED);
			repository.save(payment);
			kafkaTemplate.send("payment-success", orderDTO);
		}else {
			payment.setStatus(PaymentStatus.REJECTED);
			repository.save(payment);
			kafkaTemplate.send("payment-failed", orderDTO);
		}
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
