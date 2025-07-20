package com.santt4na.flowstore_notification.dto;

public record NotificationRequestDTO(
	String emailTarget,
	String title,
	String message,
	String userName,
	String orderId,
	String productName,
	String totalAmount
) {}
