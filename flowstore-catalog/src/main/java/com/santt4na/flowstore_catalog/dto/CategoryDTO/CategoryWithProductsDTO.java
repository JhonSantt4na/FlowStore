package com.santt4na.flowstore_catalog.dto.CategoryDTO;

import com.santt4na.flowstore_catalog.dto.Product.ProductSimpleDTO;

import java.time.LocalDateTime;
import java.util.List;

public record CategoryWithProductsDTO(
	Long id,
	String name,
	String description,
	String imgUrl,
	LocalDateTime createAt,
	LocalDateTime updateAt,
	List<ProductSimpleDTO> products
) {}