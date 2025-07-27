package com.santt4na.flowstore_order.service;

import com.santt4na.dtos.Catalog.ProductDTO;
import com.santt4na.dtos.Order.OrderDTO;
import com.santt4na.enums.OrderStatus;
import com.santt4na.flowstore_order.client.ProductClient;


import com.santt4na.flowstore_order.dto.CreateOrderDTO;
import com.santt4na.flowstore_order.dto.CreateOrderItemDTO;
import com.santt4na.flowstore_order.entity.Order;
import com.santt4na.flowstore_order.entity.OrderItem;
import com.santt4na.flowstore_order.entity.PK.OrderItemPK;
import com.santt4na.flowstore_order.events.OrderCreatedProducer;
import com.santt4na.flowstore_order.mapper.OrderMapper;
import com.santt4na.flowstore_order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
	
	public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductClient productClient, OrderCreatedProducer orderProducer) {
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.productClient = productClient;
		this.orderProducer = orderProducer;
	}
	
	@Autowired
	private final OrderCreatedProducer orderProducer;
	
	@Autowired
	private final OrderRepository orderRepository;
	
	@Autowired
	private final OrderMapper orderMapper;
	
	@Autowired
	private final ProductClient productClient;
	
	public OrderDTO createOrder(CreateOrderDTO orderDTO) {
		
		Double totalAmount = orderDTO.items().stream()
			.mapToDouble(item -> item.price() * item.quantity())
			.sum();
		
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setOrderStatus(orderDTO.orderStatus());
		order.setClientId(orderDTO.clientId());
		order.setAmount(totalAmount);
		
		Set<OrderItem> items = orderDTO.items().stream().map(itemDTO -> {
			OrderItemPK pk = new OrderItemPK();
			pk.setOrder(order);
			pk.setProductId(itemDTO.productId());
			
			OrderItem oi = new OrderItem();
			oi.setId(pk);
			oi.setQuantity(itemDTO.quantity());
			oi.setPrice(itemDTO.price());
			return oi;
		}).collect(Collectors.toSet());
		
		order.setItems(items);
		
		Order saved = orderRepository.save(order);
		
		orderProducer.sendOrderCreatedEvent(orderMapper.toDto(saved));
		return orderMapper.toDto(saved);
	}
	
	
	public OrderDTO updateOrder(Long id,OrderDTO orderDTO) {
		Order existingOrder = orderRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + id));
		
		existingOrder.setOrderStatus(orderDTO.orderStatus());
		existingOrder.setClientId(orderDTO.clientId());
		existingOrder.setMoment(orderDTO.moment());
		
		existingOrder.getItems().clear();
		orderDTO.items().forEach(itemDTO -> {
			
			ProductDTO productDTO = productClient.findProductById(itemDTO.productId().id());
			if (productDTO == null) {
				throw new IllegalArgumentException("Product Not Found: " + itemDTO.productId());
			}
			
			OrderItem item = new OrderItem();
			item.getId().setOrder(existingOrder);
			item.getId().setProductId(itemDTO.productId().id());
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
	
	public void cancelOrder(Long orderId, String reason) {
		Order order = orderRepository.findById(orderId).orElseThrow();
		order.setOrderStatus(OrderStatus.CANCELED);
		orderRepository.save(order);
		System.out.println("Order Canceled: " + reason);
	}
}