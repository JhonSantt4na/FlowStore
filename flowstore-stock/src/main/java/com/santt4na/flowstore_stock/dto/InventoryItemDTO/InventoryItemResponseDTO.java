package com.santt4na.flowstore_stock.dto;

public record InventoryItemResponseDTO(
	Long id,
	Long productId,
	Integer quantityAvailable
){}
