package com.santt4na.flowstore_stock.controller;

import com.santt4na.flowstore_stock.dto.StockMovementDTO.CreateStockMovement;
import com.santt4na.flowstore_stock.dto.StockMovementDTO.StockMovementResponseDTO;
import com.santt4na.flowstore_stock.service.StockMovementService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/stock")
@AllArgsConstructor
public class StockMovementController {
	
	@Autowired
	private final StockMovementService service;
	
	@PostMapping("/movement")
	public ResponseEntity<StockMovementResponseDTO> registerMovement(@RequestBody CreateStockMovement dto){
		log.info("Registering stock movement for product ID: {} with moviment: {}", dto.inventoryItemId(), dto.movementType());
		StockMovementResponseDTO newMovement = service.registerMovement(dto);
		return ResponseEntity.ok(newMovement);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<StockMovementResponseDTO>> listAllMovement(){
		log.info("Listing all stock movements");
		List<StockMovementResponseDTO> newMovement = service.listAll();
		return ResponseEntity.ok(newMovement);
	}
	
}