package com.e_commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.entity.Categories;
import com.e_commerce.services.CategoriesServices;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = { "http://127.0.0.1:5500","null" }) 
public class CategoriesControllers {

	
	@Autowired
	private CategoriesServices categoriesServices;
	
	
	@GetMapping("/getAllcategories")
	public ResponseEntity<List<String>>allCategories(){
		List<Categories>list=categoriesServices.getAllCategories();
    	
	List<String>listdto=	list.stream().map(e->e.getCategories_type()).toList();
		return ResponseEntity.ok(listdto);
		
	}
	
}
