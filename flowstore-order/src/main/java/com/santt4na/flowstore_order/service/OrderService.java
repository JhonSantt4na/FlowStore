package com.santt4na.flowstore_order.service;

import com.santt4na.dtos.catalog.ProductDTO;
import com.santt4na.flowstore_order.client.ProductClient;
import com.santt4na.flowstore_order.dto.OrderDTO;
import com.santt4na.flowstore_order.entity.Order;
import com.santt4na.flowstore_order.entity.OrderItem;
import com.santt4na.flowstore_order.entity.PK.OrderItemPK;
import com.santt4na.flowstore_order.mapper.OrderMapper;
import com.santt4na.flowstore_order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
	
	public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductClient productClient) {
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.productClient = productClient;
	}
	
	@Autowired
	private final OrderRepository orderRepository;
	
	@Autowired
	private final OrderMapper orderMapper;
	
	@Autowired
	private final ProductClient productClient;
	
	
	public OrderDTO createOrder(@Valid OrderDTO orderDTO) {
		
		orderDTO.items().forEach(item -> {
			ProductDTO productDTO = productClient.findProductById(item.productId());
			if (productDTO == null) {
				throw new IllegalArgumentException("Product Not found: " + item.productId());
			}
		});
		
		Order order = new Order();
		order.setMoment(orderDTO.moment());
		order.setOrderStatus(orderDTO.orderStatus());
		order.setClientId(orderDTO.clientId());
		
		Order savedOrder = orderRepository.save(order);
		
		Order finalSavedOrder = savedOrder;
		Set<OrderItem> items = orderDTO.items().stream().map(itemDTO -> {
			OrderItemPK pk = new OrderItemPK();
			pk.setOrder(finalSavedOrder);
			pk.setProductId(itemDTO.productId());
			
			OrderItem orderItem = new OrderItem();
			orderItem.setId(pk);
			orderItem.setQuantity(itemDTO.quantity());
			orderItem.setPrice(itemDTO.price());
			return orderItem;
		}).collect(Collectors.toSet());
		
		savedOrder.setItems(items);
		
		savedOrder = orderRepository.save(savedOrder);
		
		return orderMapper.toDto(savedOrder);
	}
	
	
	public OrderDTO updateOrder(Long id, @Valid OrderDTO orderDTO) {
		Order existingOrder = orderRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + id));
		
		existingOrder.setOrderStatus(orderDTO.orderStatus());
		existingOrder.setClientId(orderDTO.clientId());
		existingOrder.setMoment(orderDTO.moment());
		
		existingOrder.getItems().clear();
		orderDTO.items().forEach(itemDTO -> {
			
			ProductDTO productDTO = productClient.findProductById(itemDTO.productId());
			if (productDTO == null) {
				throw new IllegalArgumentException("Product Not Found: " + itemDTO.productId());
			}
			
			OrderItem item = new OrderItem();
			item.getId().setOrder(existingOrder);
			item.getId().setProductId(itemDTO.productId());
			item.setQuantity(itemDTO.quantity());
			item.setPrice(itemDTO.price());
			
			existingOrder.getItems().add(item);
		});
		
		Order updatedOrder = orderRepository.save(existingOrder);
		return orderMapper.toDto(updatedOrder);
	}
	
	public List<OrderDTO> listAllOrder() {
		List<Order> orders = orderRepository.findAll();
		return orders.stream()
			.map(orderMapper::toDto)
			.toList();
	}
	
	public OrderDTO findByIdOrder(Long id) {
		Order order = orderRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + id));
		return orderMapper.toDto(order);
	}
	
	public void deleteOrder(Long id) {
		if (!orderRepository.existsById(id)) {
			throw new EntityNotFoundException("Order not found with ID: " + id);
		}
		orderRepository.deleteById(id);
	}
	
	public void processOrder(Long productId) {
		ProductDTO product = productClient.findProductById(productId);
		System.out.println("Product received from the catalog: " + product.name());
	}
}