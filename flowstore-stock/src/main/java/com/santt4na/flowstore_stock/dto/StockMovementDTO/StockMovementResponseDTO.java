package com.santt4na.flowstore_stock.dto.StockMovementDTO;

import com.santt4na.enums.MovementType;

import java.time.LocalDateTime;

public record StockMovementRerponseDTO (
	Long id,
	Long inventoryId,
	MovementType movementType,
	Integer quantity,
	LocalDateTime movementDate
){}
