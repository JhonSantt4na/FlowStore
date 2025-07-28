package com.santt4na.flowstore_catalog.controller;

import com.santt4na.flowstore_catalog.dto.Product.ProductCategoryUpdateDTO;
import com.santt4na.flowstore_catalog.dto.Product.ProductDTO;
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
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO product){
		ProductDTO updated = productService.updateProduct(id, product);
		return ResponseEntity.ok().body(updated);
	}
	
	@PatchMapping("/{id}/category")
	public ResponseEntity<ProductDTO> updateCategory(
		@PathVariable Long id,
		@RequestBody @Valid ProductCategoryUpdateDTO dto) {
		ProductDTO updated = productService.updateProductCategory(id, dto);
		return ResponseEntity.ok(updated);
	}
	
	@GetMapping(
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> listAll(){
		List<ProductDTO> listOfProducts = productService.listAllProduct();
		return ResponseEntity.ok(listOfProducts);
	}
	
	@GetMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
		ProductDTO findProductId = productService.findByIdProduct(id);
		return ResponseEntity.ok().body(findProductId);
	}
	
	@GetMapping(
		value = "/name/{name}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> findByName(@PathVariable String name){
		ProductDTO findProductByName = productService.findByIdNameProduct(name);
		return ResponseEntity.ok().body(findProductByName);
	}
	
	@DeleteMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
}