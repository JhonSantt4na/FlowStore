package com.santt4na.flowstore_catalog.dto;

import java.util.UUID;

public record ProductDTO(
	Long id, String name, Double price, String description, String currency, String imgUrl,Long categoryId) {
}