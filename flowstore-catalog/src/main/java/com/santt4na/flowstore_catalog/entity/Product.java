package com.santt4na.flowstore_catalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Product implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "product_name", unique = true, nullable = false)
	private String name;
	
	@NotNull
	@Column(name = "product_price", unique = true, nullable = false)
	private Double price;
	
	private String description;
	private String currency;
	private String imgUrl;
	private Long categoryId;
	
	public Product() {
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(id, product.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}