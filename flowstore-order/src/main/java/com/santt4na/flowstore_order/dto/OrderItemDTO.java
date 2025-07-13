package com.santt4na.flowstore_order.dto;

public record OrderItemDTO(
	Long orderId,
	Long productId,
	Integer quantity,
	Double price
) {}