package com.santt4na.flowstore_catalog.service;

import com.santt4na.flowstore_catalog.dto.ProductDTO;
import com.santt4na.flowstore_catalog.entity.Product;
import com.santt4na.flowstore_catalog.mapper.ProductMapper;
import com.santt4na.flowstore_catalog.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
	
	@Autowired
	private ProductMapper mapper;
	
	@Autowired
	private ProductRepository repository;
	
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product newProduct = mapper.toEntity(productDTO);
		Product saved = repository.save(newProduct);
		return mapper.toDTO(saved);
	}
	
	public List<ProductDTO> listAllProduct(){
		return null;
	}
	
	public ProductDTO findByIdProduct(Long id){
		return null;
	}
	
	
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Product existingProduct = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Product with Id: " + id + " not found!"));
		
		existingProduct.setName(productDTO.name());
		existingProduct.setDescription(productDTO.description());
		existingProduct.setPrice(productDTO.price());
		existingProduct.setCategoryId(productDTO.categoryId());
		
		Product updatedProduct = repository.save(existingProduct);
		return mapper.toDTO(updatedProduct);
	}
	
	
	
	public ProductDTO findByIdNameProduct(String name){
		return null;
	}
	
	public void deleteProduct(Long id){
	}
	
}
