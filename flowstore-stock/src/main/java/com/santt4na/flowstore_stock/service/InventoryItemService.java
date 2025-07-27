package com.santt4na.flowstore_stock.service;

import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.dtos.Order.OrderItemDTO;
import com.santt4na.flowstore_stock.dto.InventoryItemDTO.CreateInventoryItemDTO;
import com.santt4na.flowstore_stock.dto.InventoryItemDTO.InventoryItemResponseDTO;
import com.santt4na.flowstore_stock.entity.InventoryItem;
import com.santt4na.flowstore_stock.mapper.InventoryItemMapper;
import com.santt4na.flowstore_stock.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryItemService {
	
	@Autowired
	private KafkaTemplate<String, OrderDTO> kafkaTemplate;
	
	@Autowired
	private final InventoryItemRepository repositoryInventoryItem;
	
	@Autowired
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
	
	public void processOrderStock(OrderDTO dto) {
		boolean allAvailable = true;
		
		for (OrderItemDTO itemDTO : dto.items()) {
			InventoryItem inventory = repositoryInventoryItem.findByProductId(itemDTO.productId().id())
				.orElseThrow(() -> new RuntimeException("Inventory not found for the product"));
			
			if (inventory == null || inventory.getQuantityAvailable() < itemDTO.quantity()) {
				allAvailable = false;
				break;
			}
		}
		
		if (allAvailable){
			for (OrderItemDTO item : dto.items()){
				InventoryItem inventory = repositoryInventoryItem.findByProductId(item.productId().id())
					.orElseThrow(() -> new RuntimeException("Inventory not found for the product"));
				
				inventory.setQuantityAvailable(inventory.getQuantityAvailable() - item.quantity());
				repositoryInventoryItem.save(inventory);
			}
			kafkaTemplate.send("stock-success", dto);
		}else {
			kafkaTemplate.send("stock-failed", dto);
		}
		
	}
	
}
