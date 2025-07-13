package com.santt4na.flowstore_catalog.mapper;

import com.santt4na.flowstore_catalog.dto.CategoryDTO.CategoryCreateDTO;
import com.santt4na.flowstore_catalog.dto.CategoryDTO.CategoryDTO;
import com.santt4na.flowstore_catalog.dto.CategoryDTO.CategoryWithProductsDTO;
import com.santt4na.flowstore_catalog.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
	CategoryDTO toDTO(Category category);
	
	@Mapping(target = "products", source = "products")
	CategoryWithProductsDTO toDTOWithProducts(Category category);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "products", ignore = true)
	@Mapping(target = "createAt", ignore = true)
	@Mapping(target = "updateAt", ignore = true)
	Category toEntity(CategoryCreateDTO dto);
	
	List<CategoryDTO> toDTOList(List<Category> categories);
}