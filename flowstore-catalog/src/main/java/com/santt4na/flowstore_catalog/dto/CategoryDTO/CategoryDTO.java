package com.santt4na.flowstore_catalog.dto.CategoryDTO;

import java.time.LocalDateTime;

public record CategoryDTO(
	Long id,
	String name,
	String description,
	String imgUrl,
	LocalDateTime createAt,
	LocalDateTime updateAt
) {}
