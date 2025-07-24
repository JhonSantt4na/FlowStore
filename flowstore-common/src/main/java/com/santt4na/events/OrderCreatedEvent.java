package com.santt4na.events;

import java.time.LocalDateTime;
import java.util.List;

public class OrderCreatedEvent {
	private Long orderId;
	private Long clientId;
	private List<OrderItemData> items;
	private Double totalAmount;
	private LocalDateTime createdAt;
	
	public OrderCreatedEvent() {
	}
	
	public OrderCreatedEvent(Long orderId, Long clientId, List<OrderItemData> items, Double totalAmount, LocalDateTime createdAt) {
		this.orderId = orderId;
		this.clientId = clientId;
		this.items = items;
		this.totalAmount = totalAmount;
		this.createdAt = createdAt;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getClientId() {
		return clientId;
	}
	
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	public List<OrderItemData> getItems() {
		return items;
	}
	
	public void setItems(List<OrderItemData> items) {
		this.items = items;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public static class OrderItemData{
		private Long productId;
		private Integer quantity;
		private Double price;
		
		public Long getProductId() {
			return productId;
		}
		
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		
		public Integer getQuantity() {
			return quantity;
		}
		
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		
		public Double getPrice() {
			return price;
		}
		
		public void setPrice(Double price) {
			this.price = price;
		}
	}
	
}
