package com.santt4na.flowstore_stock.service;

import com.santt4na.flowstore_stock.dto.InventoryItemDTO.CreateInventoryItemDTO;
import com.santt4na.flowstore_stock.dto.InventoryItemDTO.InventoryItemResponseDTO;
import com.santt4na.flowstore_stock.entity.InventoryItem;
import com.santt4na.flowstore_stock.mapper.InventoryItemMapper;
import com.santt4na.flowstore_stock.repository.InventoryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryItemService {
	
	private final InventoryItemRepository repositoryInventoryItem;
	private final InventoryItemMapper mapper;
	
	public InventoryItemService(InventoryItemRepository repositoryInventoryItem, InventoryItemMapper mapper) {
		this.repositoryInventoryItem = repositoryInventoryItem;
		this.mapper = mapper;
	}
	
	public InventoryItemResponseDTO create(CreateInventoryItemDTO inventoryItemDto){
		repositoryInventoryItem.findByProductId(inventoryItemDto.productId())
			.ifPresent(inventoryItem -> {
				throw new RuntimeException("There is already inventory for this product");
			});
		
		InventoryItem newItem = mapper.toEntity(inventoryItemDto);
		newItem.setProductId(inventoryItemDto.productId());
		newItem.setQuantityAvailable(inventoryItemDto.quantityInitial());
		repositoryInventoryItem.save(newItem);
		
		return mapper.toDTO(newItem);
	}
	
	public InventoryItemResponseDTO getById(Long id){
		InventoryItem item = repositoryInventoryItem.findById(id)
			.orElseThrow(() -> new RuntimeException("Inventory not found"));
		return mapper.toDTO(item);
		
	}
	
	public List<InventoryItemResponseDTO> getAll() {
		return repositoryInventoryItem.findAll().stream()
			.map(mapper::toDTO)
			.collect(Collectors.toList());
	}
	
	public InventoryItemResponseDTO updatedQuantity(Long productId, int qty){
		InventoryItem item = repositoryInventoryItem.findByProductId(productId)
			.orElseThrow(() -> new RuntimeException("Inventory not found for the product"));
		
		item.setQuantityAvailable(qty);
		repositoryInventoryItem.save(item);
		return mapper.toDTO(item);
	}
	
	public void delete(Long id){
	if (!repositoryInventoryItem.existsById(id)){
		throw new RuntimeException("Inventory not found");
	}
	InventoryItemResponseDTO deleted = this.getById(id);
	repositoryInventoryItem.delete(mapper.toEntity(deleted));
	}
	
}
