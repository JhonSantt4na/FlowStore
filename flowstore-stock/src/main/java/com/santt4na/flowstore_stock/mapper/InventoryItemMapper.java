package com.santt4na.flowstore_stock.mapper;

import com.santt4na.flowstore_stock.dto.InventoryItemDTO.CreateInventoryItemDTO;
import com.santt4na.flowstore_stock.dto.InventoryItemDTO.InventoryItemResponseDTO;
import com.santt4na.flowstore_stock.entity.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {
	
	InventoryItemResponseDTO toDTO(InventoryItem inventoryItem);
	
	@Mapping(target = "quantityInitial", ignore = true)
	CreateInventoryItemDTO toDto(InventoryItem inventoryItem);
	
	InventoryItem toEntity(InventoryItemResponseDTO dto);
	
	@Mapping(target = "quantityAvailable", ignore = true)
	@Mapping(target = "id", ignore = true)
	InventoryItem toEntity(CreateInventoryItemDTO dto);
	
}