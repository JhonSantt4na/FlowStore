package com.santt4na.flowstore_catalog.repository;

import com.santt4na.flowstore_catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
	public interface ProductRepository extends JpaRepository<Product, Long> {
}