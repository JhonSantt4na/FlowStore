package com.santt4na.flowstore_catalog.dto.CategoryDTO;

import jakarta.validation.constraints.NotBlank;

public record CategoryCreateDTO(
	@NotBlank(message = "The name is mandatory") String name,
	String description,
	String imgUrl
) {}