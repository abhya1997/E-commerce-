package com.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductsDto {
	
	private String product_name;
	private String description;
	private double price;

}
