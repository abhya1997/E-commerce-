package com.e_commerce.entity;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="categories")
@Data
public class Categories {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categorieId;
	private String categories_type;
	
	@OneToMany(mappedBy = "categories")
	@Cascade(CascadeType.ALL)
	@JsonManagedReference("categories-product")
	private List<Products>productList;
	
}
