package com.santt4na.flowstore_order.mapper;

import com.santt4na.flowstore_order.dto.OrderDTO;
import com.santt4na.flowstore_order.dto.OrderItemDTO;
import com.santt4na.flowstore_order.entity.Order;
import com.santt4na.flowstore_order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	
	@Mapping(target = "items", source = "items")
	OrderDTO toDto(Order order);
	
	@Mapping(target = "productId", source = "")
	@Mapping(target = "id.productId", source = "productId")
	OrderItemDTO toDto(OrderItem orderItem);
	
	Set<OrderItemDTO> mapItems(Set<OrderItem> items);
	
	
	Order toEntity(OrderDTO dto);
	OrderItem toEntity(OrderItemDTO dto);
}