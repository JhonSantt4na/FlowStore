package com.santt4na.flowstore_order.dto;

public record CreateOrderItemDTO(
	Long productId,
	Integer quantity,
	Double price
) {}
