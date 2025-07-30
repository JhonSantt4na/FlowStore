package com.santt4na.flowstore_stock.controller;

import com.santt4na.flowstore_stock.dto.InventoryItemDTO.CreateInventoryItemDTO;
import com.santt4na.flowstore_stock.dto.InventoryItemDTO.InventoryItemResponseDTO;
import com.santt4na.flowstore_stock.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryItemController {

	@Autowired
	private final InventoryItemService service;
	
	@PostMapping
	public ResponseEntity<InventoryItemResponseDTO> create(@Valid @RequestBody CreateInventoryItemDTO dto){
		log.info("Creating inventory record for product ID: {}", dto.productId());
		return ResponseEntity.ok(service.create(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<InventoryItemResponseDTO>> getAll(){
		log.warn("List all Inventory Item");
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InventoryItemResponseDTO> getById(@PathVariable Long id){
		log.info("Deleting inventory record for product ID: {}", id);
		return ResponseEntity.ok(service.getById(id));
	}
	
	@PutMapping("/{productId}/quantity")
	public ResponseEntity<InventoryItemResponseDTO> updateQuantity(@PathVariable Long productId, @RequestParam int quantity) {
		log.info("Updating inventory for product ID: {} with quantity: {}", productId, quantity);
		return ResponseEntity.ok(service.updatedQuantity(productId,quantity));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		log.info("Deleting inventory for product ID: {}", id);
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}