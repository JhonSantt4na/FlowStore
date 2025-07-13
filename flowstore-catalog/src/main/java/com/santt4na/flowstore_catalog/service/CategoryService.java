package com.santt4na.flowstore_catalog.service;

import com.santt4na.flowstore_catalog.dto.CategoryDTO.CategoryCreateDTO;
import com.santt4na.flowstore_catalog.dto.CategoryDTO.CategoryDTO;
import com.santt4na.flowstore_catalog.entity.Category;
import com.santt4na.flowstore_catalog.mapper.CategoryMapper;
import com.santt4na.flowstore_catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
	
	public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@Autowired
	private final CategoryRepository repository;
	
	@Autowired
	private final CategoryMapper mapper;
	
	public CategoryDTO createCategory(CategoryCreateDTO categoryDTO) {
		Category newCategory = mapper.toEntity(categoryDTO);
		Category saved = repository.save(newCategory);
		return mapper.toDTO(saved);
	}
	
	public List<CategoryDTO> listAllCategory(){
		List<Category> category = repository.findAll();
		return mapper.toDTOList(category);
	}
	
	public CategoryDTO findByIdCategory(Long id){
		Category findCategory = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Category Not Found!"));
		return mapper.toDTO(findCategory);
	}
	
	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
		Category existingCategory = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Category with Id: " + id + " not found!"));
		
		existingCategory.setName(categoryDTO.name());
		existingCategory.setDescription(categoryDTO.description());
		existingCategory.setImgUrl(categoryDTO.imgUrl());
		
		Category updatedCategory = repository.save(existingCategory);
		return mapper.toDTO(updatedCategory);
	}
	
	public CategoryDTO findByIdNameCategory(String name){
		Category findCategoryName = repository.findByName(name)
			.orElseThrow(() -> new RuntimeException("Category Not Found!"));
		return mapper.toDTO(findCategoryName);
	}
	
	public void deleteCategory(Long id){
		Category CategoryDelete = repository.findById(id)
			.orElseThrow(()-> new RuntimeException("Category Not Found"));
		repository.delete(CategoryDelete);
	}
}
