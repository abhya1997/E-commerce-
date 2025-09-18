package com.e_commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.entity.Categories;
import com.e_commerce.repo.CategoriesRepo;

@Service
public class CategoriesServices {

	@Autowired
	private CategoriesRepo categoriesRepo;
	
	public List<Categories> getAllCategories() {
		return categoriesRepo.findAll();
		
	}
	
	
	
}
