package com.e_commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.entity.Categories;
import com.e_commerce.entity.Products;
import com.e_commerce.repo.CategoriesRepo;
import com.e_commerce.repo.ProductsRepo;

@Service
public class ProductServices {

	@Autowired
	private ProductsRepo productsRepo;

	@Autowired
	private CategoriesRepo categoriesrepo;

	public List<Products> getAllProducts() {
		return productsRepo.findAll();

	}

	public List<Products> getAProductsByCategories(String str) {
        Categories categories=categoriesrepo.findByType(str);
        System.out.println(categories.getCategorieId());
		return productsRepo.findByCategoriesCategorieId(categories.getCategorieId());
	}

}
