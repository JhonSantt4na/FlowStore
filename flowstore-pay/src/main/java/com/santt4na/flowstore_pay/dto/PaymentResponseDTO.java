package com.santt4na.flowstore_pay.dto;

import com.santt4na.flowstore_pay.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentResponseDTO(
	UUID id,
	String orderId,
	Double amount,
	PaymentStatus status,
	LocalDateTime createdAt
) {
}