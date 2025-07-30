package com.santt4na.flowstore_catalog.controller;

import com.santt4na.flowstore_catalog.dto.Product.ProductCategoryUpdateDTO;
import com.santt4na.flowstore_catalog.dto.Product.ProductDTO;
import com.santt4na.flowstore_catalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

	private ProductService productService;
	
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO product){
		log.info("Creating new product: {}", product.name());
		ProductDTO created = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping(value = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO product){
		log.info("Updating product with ID: {}", product.id());
		ProductDTO updated = productService.updateProduct(id, product);
		return ResponseEntity.ok().body(updated);
	}
	
	@PatchMapping("/{id}/category")
	public ResponseEntity<ProductDTO> updateCategory(
		@PathVariable Long id,
		@RequestBody @Valid ProductCategoryUpdateDTO dto) {
		log.info("Updating Category of the Product with ID: {}", dto.categoryId());
		ProductDTO updated = productService.updateProductCategory(id, dto);
		return ResponseEntity.ok(updated);
	}
	
	@GetMapping(
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> listAll(){
		log.info("List All Products");
		List<ProductDTO> listOfProducts = productService.listAllProduct();
		return ResponseEntity.ok(listOfProducts);
	}
	
	@GetMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
		log.info("Retrieving product with ID: {}", id);
		ProductDTO findProductId = productService.findByIdProduct(id);
		return ResponseEntity.ok().body(findProductId);
	}
	
	@GetMapping(
		value = "/name/{name}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> findByName(@PathVariable String name){
		log.info("Updating product with Name: {}", name);
		ProductDTO findProductByName = productService.findByIdNameProduct(name);
		return ResponseEntity.ok().body(findProductByName);
	}
	
	@DeleteMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		log.info("Deleting product with ID: {}", id);
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
}