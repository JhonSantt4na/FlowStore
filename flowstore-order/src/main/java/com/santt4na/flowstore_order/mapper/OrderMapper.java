package com.santt4na.flowstore_order.mapper;

import com.santt4na.dtos.Catalog.ProductDTO;
import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.dtos.Order.OrderItemDTO;
import com.santt4na.flowstore_order.entity.Order;
import com.santt4na.flowstore_order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	
	OrderDTO toDto(Order order);
	
	@Mapping(target = "productId", source = "id.productId")
	OrderItemDTO toDto(OrderItem orderItem);
	
	Set<OrderItemDTO> mapItems(Set<OrderItem> items);
	
	Order toEntity(OrderDTO dto);
	OrderItem toEntity(OrderItemDTO dto);
	
	default ProductDTO map(Long productId) {
		if (productId == null) return null;
		return new ProductDTO(productId,null,null,null);
	}
}