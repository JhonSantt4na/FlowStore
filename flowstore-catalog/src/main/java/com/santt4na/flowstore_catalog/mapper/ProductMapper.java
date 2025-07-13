package com.santt4na.flowstore_catalog.mapper;

import com.santt4na.flowstore_catalog.dto.Product.ProductDTO;
import com.santt4na.flowstore_catalog.dto.Product.ProductSimpleDTO;
import com.santt4na.flowstore_catalog.entity.Category;
import com.santt4na.flowstore_catalog.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
	
	@Mapping(target = "category", source = "categoryId", qualifiedByName = "mapIdToCategory")
	Product toEntity(ProductDTO dto);
	
	@Mapping(target = "categoryId", source = "category.id")
	ProductDTO toDTO(Product product);
	
	@Named("mapIdToCategory")
	default Category mapIdToCategory(Long id) {
		if (id == null) return null;
		Category category = new Category();
		category.setId(id);
		return category;
	}
}