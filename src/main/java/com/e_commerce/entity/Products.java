package com.e_commerce.entity;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productsId;
	private String product_name;
	private String description;
	private double price;
	private Integer quantity;
	private double discount;
	private double specialPrice;

	@OneToMany(mappedBy = "products")
	@Cascade(CascadeType.ALL)
	@JsonManagedReference("product-cartItem")
	private List<CartItems>cartItemsList;
	
	@ManyToOne
	@JoinColumn(name="categorieId",referencedColumnName = "categorieId")
	@JsonBackReference("categories-product")
	private Categories categories;
	
}
