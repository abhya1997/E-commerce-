package com.e_commerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="orderItem")
@Data
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonBackReference
	private Products product;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonBackReference
	private Orders order;
	
	private Integer quantity;
	private double discount;
	private double orderedProductPrice;
	

}
