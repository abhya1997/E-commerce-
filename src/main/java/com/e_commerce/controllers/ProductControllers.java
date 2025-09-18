package com.e_commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.dto.ProductsDto;
import com.e_commerce.entity.Products;
import com.e_commerce.services.ProductServices;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = { "http://127.0.0.1:5500" ,"null"}) 
public class ProductControllers {
	
	@Autowired
	private ProductServices productServices;
	
	@GetMapping("/productsList")
	public ResponseEntity<List<ProductsDto>>findProducts(){
		 List<Products>list=productServices.getAllProducts();
	List<ProductsDto>listDto=list.stream().map(e->new ProductsDto(e.getProduct_name(),e.getDescription(),e.getPrice())).toList();
	return ResponseEntity.ok(listDto);
	}
	@GetMapping("/products/{str}")
	public ResponseEntity<List<ProductsDto>>findByCategories(@PathVariable String str){
		List<Products>list=productServices.getAProductsByCategories(str);
		List<ProductsDto>listDto=list.stream().map(e->new ProductsDto(e.getProduct_name(),e.getDescription(),e.getPrice())).toList();
		return ResponseEntity.ok(listDto);
	}

}
