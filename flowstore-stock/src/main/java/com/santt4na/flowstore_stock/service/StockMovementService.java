package com.santt4na.flowstore_stock.service;

import com.santt4na.enums.MovementType;
import com.santt4na.flowstore_stock.dto.StockMovementDTO.CreateStockMovement;
import com.santt4na.flowstore_stock.dto.StockMovementDTO.StockMovementResponseDTO;
import com.santt4na.flowstore_stock.entity.InventoryItem;
import com.santt4na.flowstore_stock.entity.StockMovement;
import com.santt4na.flowstore_stock.mapper.InventoryItemMapper;
import com.santt4na.flowstore_stock.mapper.StockMovementMapper;
import com.santt4na.flowstore_stock.repository.InventoryItemRepository;
import com.santt4na.flowstore_stock.repository.StockMovementRepository;
import org.springframework.stereotype.Service;

@Service
public class StockMovementService {
	private final StockMovementRepository repositoryStock;
	private final InventoryItemRepository repositoryInventory;
	private final StockMovementMapper mapperStock;
	private final InventoryItemMapper mapperInventory;
	
	public StockMovementService(StockMovementRepository repositoryStock, InventoryItemRepository repositoryInventory, StockMovementMapper mapperStock, InventoryItemMapper mapperInventory) {
		this.repositoryStock = repositoryStock;
		this.repositoryInventory = repositoryInventory;
		this.mapperStock = mapperStock;
		this.mapperInventory = mapperInventory;
	}

	public StockMovementResponseDTO registerMovement(CreateStockMovement dto){
		InventoryItem item = repositoryInventory.findById(dto.inventoryItemId())
			.orElseThrow(()-> new RuntimeException("Inventory not found"));
		
		int currentQty = item.getQuantityAvailable();
		int newQty = dto.movementType() == MovementType.IN
			? currentQty + dto.quantity()
			: currentQty - dto.quantity();
		
		if (newQty < 0) {
			throw new RuntimeException("Insufficient amount in stock");
		}
		
		item.setQuantityAvailable(newQty);
		repositoryInventory.save(item);
		
		StockMovement movement = mapperStock.toEntity(dto);
		repositoryStock.save(movement);
		
		return mapperStock.toDTO(movement);
	}
	
}