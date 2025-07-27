package com.santt4na.dtos.Catalog;

import java.math.BigDecimal;

public record ProductDTO(
	Long id,
	String name,
	BigDecimal price,
	String description) {}