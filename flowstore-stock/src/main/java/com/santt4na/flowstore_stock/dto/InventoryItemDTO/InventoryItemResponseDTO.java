package com.santt4na.flowstore_stock.dto.InventoryItemDTO;

public record InventoryItemResponseDTO(
	Long id,
	Long productId,
	Integer quantityAvailable
){}
