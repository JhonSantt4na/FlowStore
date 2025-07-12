package com.santt4na.flowstore_catalog.mapper;

import com.santt4na.flowstore_catalog.dto.ProductDTO;
import com.santt4na.flowstore_catalog.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	
	Product toEntity(ProductDTO dto);
	
	ProductDTO toDTO(Product product);
}