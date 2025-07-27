package com.santt4na.flowstore_order.client;

import com.santt4na.dtos.Catalog.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
	name = "catalog-service",
	url = "${catalog-service.url}")
//	configuration = FeignConfig.class)
public interface ProductClient {
	
	@GetMapping("/api/product/{id}")
	ProductDTO findProductById(@PathVariable Long id);
}