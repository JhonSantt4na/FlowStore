package com.santt4na.flowstore_order.service;

import com.santt4na.dtos.catalog.ProductDTO;
import com.santt4na.flowstore_order.client.ProductClient;
import com.santt4na.flowstore_order.dto.OrderDTO;
import com.santt4na.flowstore_order.entity.Order;
import com.santt4na.flowstore_order.entity.OrderItem;
import com.santt4na.flowstore_order.mapper.OrderMapper;
import com.santt4na.flowstore_order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
		// Validar se produtos existem no catálogo
		orderDTO.items().forEach(item -> {
			ProductDTO productDTO = productClient.findProductById(item.productId());
			if (productDTO == null) {
				throw new IllegalArgumentException("Produto não encontrado: " + item.productId());
			}
		});
		
		Order order = orderMapper.toEntity(orderDTO);
		Order savedOrder = orderRepository.save(order);
		return orderMapper.toDto(savedOrder);
	}
	
	public OrderDTO updateOrder(Long id, @Valid OrderDTO orderDTO) {
		Order existingOrder = orderRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com id: " + id));
		
		// Atualiza os dados (exceto id)
		existingOrder.setOrderStatus(orderDTO.orderStatus());
		existingOrder.setClientId(orderDTO.clientId());
		existingOrder.setMoment(orderDTO.moment());
		
		// Atualizar itens: para simplicidade, limpar e recriar
		existingOrder.getItems().clear();
		orderDTO.items().forEach(itemDTO -> {
			// Validar produto via ProductClient
			ProductDTO productDTO = productClient.findProductById(itemDTO.productId());
			if (productDTO == null) {
				throw new IllegalArgumentException("Produto não encontrado: " + itemDTO.productId());
			}
			
			OrderItem item = new OrderItem();
			item.getId().setOrder(existingOrder);
			item.getId().setProductId(itemDTO.productId());
			item.setQuantity(itemDTO.quantity());
			item.setPrice(itemDTO.price());
			
			existingOrder.getItems().add(item);
		});
		
		Order updatedOrder = orderRepository.save(existingOrder);
		return orderMapper.toDTO(updatedOrder);
	}
	
	public List<OrderDTO> listAllOrder() {
		List<Order> orders = orderRepository.findAll();
		return orders.stream()
			.map(orderMapper::toDTO)
			.toList();
	}
	
	public OrderDTO findByIdOrder(Long id) {
		Order order = orderRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com id: " + id));
		return orderMapper.toDTO(order);
	}
	
	public void deleteOrder(Long id) {
		if (!orderRepository.existsById(id)) {
			throw new EntityNotFoundException("Pedido não encontrado com id: " + id);
		}
		orderRepository.deleteById(id);
	}
	
	public void processOrder(Long productId) {
		ProductDTO product = productClient.findProductById(productId);
		System.out.println("Product received from the catalog: " + product.name());
	}
}