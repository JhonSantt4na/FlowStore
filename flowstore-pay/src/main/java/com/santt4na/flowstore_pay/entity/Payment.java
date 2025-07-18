package com.santt4na.flowstore_pay.entity;

import com.santt4na.flowstore_pay.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(nullable = false, unique = true)
	private String orderId;
	
	@Column(nullable = false)
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus status;
	
	@Column
	private String transactionId;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column
	private LocalDateTime updatedAt;
	
	public Payment() {
	}
}