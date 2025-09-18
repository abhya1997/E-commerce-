package com.e_commerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cartItems")
@Data
public class CartItems {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartItemId;
	
	private Integer quantity;
	private double discount;
	private double productPrice;
	
	
	@ManyToOne
	@JoinColumn(name = "productsId",referencedColumnName = "productsId")
	@JsonBackReference("product-cartItem")
	private Products products;
	
	
	@ManyToOne
	@JoinColumn(name="cartId",referencedColumnName = "cartId")
	@JsonBackReference("cart-cartItem")
	private Cart cart;
	
	
}
