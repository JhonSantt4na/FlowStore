package com.santt4na.flowstore_stock.controller;

import com.santt4na.flowstore_stock.dto.StockMovementDTO.CreateStockMovement;
import com.santt4na.flowstore_stock.dto.StockMovementDTO.StockMovementResponseDTO;
import com.santt4na.flowstore_stock.service.StockMovementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@AllArgsConstructor
public class StockMovementController {
	
	@Autowired
	private final StockMovementService service;
	
	@PostMapping("/movement")
	public ResponseEntity<StockMovementResponseDTO> registerMovement(@RequestBody CreateStockMovement dto){
		StockMovementResponseDTO newMovement = service.registerMovement(dto);
		return ResponseEntity.ok(newMovement);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<StockMovementResponseDTO>> listAllMovement(){
		List<StockMovementResponseDTO> newMovement = service.listAll();
		return ResponseEntity.ok(newMovement);
	}
	
}