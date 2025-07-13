package com.santt4na.flowstore_catalog.dto.Product;

import com.santt4na.flowstore_catalog.entity.Category;

public record ProductDTO(
	Long id,
	String name,
	Double price,
	String description,
	String currency,
	String imgUrl,
	Long categoryId
) {}