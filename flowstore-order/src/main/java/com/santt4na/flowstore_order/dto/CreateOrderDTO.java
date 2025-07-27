package com.santt4na.flowstore_order.dto;

import com.santt4na.enums.OrderStatus;

import java.time.Instant;
import java.util.Set;

public record CreateOrderDTO(
	Instant moment,
	OrderStatus orderStatus,
	Long clientId,
	Set<CreateOrderItemDTO> items,
	Double amount
) {}