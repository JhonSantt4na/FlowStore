package com.santt4na.flowstore_stock.dto.StockMovementDTO;

import com.santt4na.enums.MovementType;

public record CreateStockMovement(
	Long inventoryItemId,
	MovementType movementType,
	int quantity
) {}