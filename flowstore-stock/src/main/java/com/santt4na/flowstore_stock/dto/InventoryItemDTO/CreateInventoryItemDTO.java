package com.santt4na.flowstore_stock.dto.InventoryItemDTO;

import jakarta.validation.constraints.NotNull;

public record CreateInventoryItemDTO (
	@NotNull Long productId,
	@NotNull Integer quantityInitial
) {}