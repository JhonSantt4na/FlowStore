package com.santt4na.flowstore_stock.repository;

import com.santt4na.flowstore_stock.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
	
	Optional<InventoryItem> findByProductId(Long productID);
}
