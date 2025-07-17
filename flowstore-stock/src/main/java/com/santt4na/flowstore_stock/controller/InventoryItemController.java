package com.santt4na.flowstore_stock.controller;

import com.santt4na.flowstore_stock.dto.InventoryItemDTO.CreateInventoryItemDTO;
import com.santt4na.flowstore_stock.dto.InventoryItemDTO.InventoryItemResponseDTO;
import com.santt4na.flowstore_stock.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryItemController {

	@Autowired
	private final InventoryItemService service;
	
	@PostMapping
	public ResponseEntity<InventoryItemResponseDTO> create(@Valid @RequestBody CreateInventoryItemDTO dto){
		return ResponseEntity.ok(service.create(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<InventoryItemResponseDTO>> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InventoryItemResponseDTO> getById(@PathVariable Long id){
		return ResponseEntity.ok(service.getById(id));
	}
	
	@PutMapping("/{productId}/quantity")
	public ResponseEntity<InventoryItemResponseDTO> updateQuantity(@PathVariable Long productId, @RequestParam int quantity) {
		return ResponseEntity.ok(service.updatedQuantity(productId,quantity));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}