package com.e_commerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.e_commerce.entity.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
	/*
	 * @Query(""" SELECT p.id AS productId, p.name AS productName, p.price AS
	 * productPrice, c.id AS categoryId, c.name AS categoryName FROM Products p JOIN
	 * p.category c WHERE c.id = :categoryId """) List<Products>
	 * findByCategoryId(@Param("categoryId") Long categoryId);
	 */
	
	//List<Products> findByCategoryType(String categoryType);
	List<Products> findByCategoriesCategorieId(Long categorieId);




}
