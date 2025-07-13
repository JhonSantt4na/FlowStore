package com.santt4na.dtos.catalog;

import java.math.BigDecimal;

public record ProductDTO(
	Long id,
	String name,
	BigDecimal price,
	String description) { }