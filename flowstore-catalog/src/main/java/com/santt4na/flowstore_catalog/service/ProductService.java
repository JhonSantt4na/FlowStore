package com.santt4na.flowstore_catalog.service;

import com.santt4na.flowstore_catalog.dto.Product.ProductDTO;
import com.santt4na.flowstore_catalog.entity.Product;
import com.santt4na.flowstore_catalog.mapper.ProductMapper;
import com.santt4na.flowstore_catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	
	public ProductService(ProductMapper mapper, ProductRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}
	
	@Autowired
	private final ProductMapper mapper;
	
	@Autowired
	private final ProductRepository repository;
	
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product newProduct = mapper.toEntity(productDTO);
		Product saved = repository.save(newProduct);
		return mapper.toDTO(saved);
	}
	
	public List<ProductDTO> listAllProduct(){
		List<Product> product = repository.findAll();
		return product.stream()
			.map(mapper::toDTO)
			.toList();
	}
	
	public ProductDTO findByIdProduct(Long id){
		Product findProduct = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Product Not Found!"));
		return mapper.toDTO(findProduct);
	}
	
	
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Product existingProduct = repository.findById(id)
			.orElseThrow(() -> new RuntimeException("Product with Id: " + id + " not found!"));
		
		existingProduct.setName(productDTO.name());
		existingProduct.setDescription(productDTO.description());
		existingProduct.setPrice(productDTO.price());
		
		Product updatedProduct = repository.save(existingProduct);
		return mapper.toDTO(updatedProduct);
	}
	
	public ProductDTO findByIdNameProduct(String name){
		Product findProductName = repository.findByName(name)
			.orElseThrow(() -> new RuntimeException("Product Not Found!"));
		return mapper.toDTO(findProductName);
	}
	
	public void deleteProduct(Long id){
		Product productDelete = repository.findById(id)
			.orElseThrow(()-> new RuntimeException("Product Not Found"));
		repository.delete(productDelete);
	}
}
