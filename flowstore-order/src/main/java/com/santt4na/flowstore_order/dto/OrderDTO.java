package com.santt4na.flowstore_order.dto;

import com.santt4na.enums.OrderStatus;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public record OrderDTO(
	Long id,
	Instant moment,
	OrderStatus orderStatus,
	Long clientId,
	Set<OrderItemDTO> items
) {}