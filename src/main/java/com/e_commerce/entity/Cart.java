package com.e_commerce.entity;

import java.util.List;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cart")
@Data
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
	@OneToOne
	@JoinColumn(name="userId",referencedColumnName = "userId")
	@JsonBackReference("user-cart")
	private User user;
	
	@OneToMany(mappedBy = "cart",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JsonManagedReference("cart-cartItem")
	private List<CartItems>productList;
	private double cartTotal;

}