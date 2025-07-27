package com.santt4na.dtos.Order;

import com.santt4na.dtos.Catalog.ProductDTO;

public record OrderItemDTO(
	Integer quantity,
	ProductDTO productId,
	Double price
) {}