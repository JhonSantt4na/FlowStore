package com.santt4na.flowstore_catalog.controller;

import com.santt4na.flowstore_catalog.dto.ProductDTO;
import com.santt4na.flowstore_catalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

	private ProductService productService;
	
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO product){
		ProductDTO created = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping(value = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProductDTO> update(@RequestParam Long id, @Valid @RequestParam ProductDTO product){
		ProductDTO updated = productService.updateProduct(id, product);
		return ResponseEntity.ok().body(updated);
	}
	
	@GetMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ProductDTO>> listAll(){
		List<ProductDTO> listOfProducts = productService.listAllProduct();
		return ResponseEntity.ok(listOfProducts);
	}
	
	@GetMapping(
		value = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProductDTO> findById(@RequestParam Long id){
		ProductDTO finded = productService.findByIdProduct(id);
		return ResponseEntity.ok().body(finded);
	}
	
	@GetMapping(
		value = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProductDTO> findByName(@RequestParam String name){
		ProductDTO finded = productService.findByIdNameProduct(name);
		return ResponseEntity.ok().body(finded);
	}
	
	@DeleteMapping(
		value = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> delete(@RequestParam Long id){
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
}
