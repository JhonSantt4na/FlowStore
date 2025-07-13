package com.santt4na.flowstore_order.entity.PK;

import com.santt4na.flowstore_order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class OrderItemPK implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(name = "product_id")
	private Long productId;
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		OrderItemPK that = (OrderItemPK) o;
		return Objects.equals(order, that.order) && Objects.equals(productId, that.productId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(order, productId);
	}
}
