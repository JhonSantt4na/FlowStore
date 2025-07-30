package com.santt4na.flowstore_catalog.controller;

import com.santt4na.flowstore_catalog.dto.CategoryDTO.CategoryCreateDTO;
import com.santt4na.flowstore_catalog.dto.CategoryDTO.CategoryDTO;
import com.santt4na.flowstore_catalog.service.CategoryService;
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
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {
	
	private CategoryService categoryService;
	
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryCreateDTO category){
		log.info("Creating new category: {}", category.name());
		CategoryDTO created = categoryService.createCategory(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@PutMapping(value = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @Valid @RequestBody CategoryDTO category){
		log.info("Updating category with ID: {}", category.id());
		CategoryDTO updated = categoryService.updateCategory(id, category);
		return ResponseEntity.ok().body(updated);
	}
	
	@GetMapping(
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDTO>> listAll(){
		log.warn("List all Category ");
		List<CategoryDTO> listOfCategory = categoryService.listAllCategory();
		return ResponseEntity.ok(listOfCategory);
	}
	
	@GetMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
		log.info("Fetching category with ID: {}", id);
		CategoryDTO findCategoryId = categoryService.findByIdCategory(id);
		return ResponseEntity.ok().body(findCategoryId);
	}
	
	@GetMapping(
		value = "/name/{name}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> findByName(@PathVariable String name){
		log.info("Fetching category with Name: {}", name);
		CategoryDTO findCategoryByName = categoryService.findByIdNameCategory(name);
		return ResponseEntity.ok().body(findCategoryByName);
	}
	
	@DeleteMapping(
		value = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable Long id){
		log.info("Deleting category with ID: {}", id);
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
}
