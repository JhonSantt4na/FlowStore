package com.santt4na.flowstore_stock.mapper;

import com.santt4na.flowstore_stock.dto.StockMovementDTO.CreateStockMovement;
import com.santt4na.flowstore_stock.dto.StockMovementDTO.StockMovementResponseDTO;
import com.santt4na.flowstore_stock.entity.StockMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StockMovementMapper {
	
	StockMovementResponseDTO toDTO(StockMovement stockMovement);
	
	@Mapping(target = "inventoryItemId", ignore = true)
	CreateStockMovement toDto(StockMovement stockMovement);
	
	StockMovement toEntity(StockMovementResponseDTO dto);
	
	@Mapping(target = "movementDate", ignore = true)
	@Mapping(target = "inventoryId", ignore = true)
	@Mapping(target = "id", ignore = true)
	StockMovement toEntity(CreateStockMovement dto);
}