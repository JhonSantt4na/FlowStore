package com.santt4na.flowstore_catalog.dto.Product;

import javax.validation.constraints.NotNull;

public record ProductCategoryUpdateDTO(
	@NotNull(message = "categoryId is required")
	Long categoryId
) {}