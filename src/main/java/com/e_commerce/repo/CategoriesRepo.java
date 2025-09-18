package com.e_commerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.e_commerce.entity.Categories;
import com.e_commerce.entity.Products;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {
	//List<Products> findByCategoryType(String categoryName);
   //List<Categories> findByCategoryType(String categoryType);
	 @Query("SELECT c FROM Categories c WHERE c.categories_type = :type")
	    Categories findByType(@Param("type") String type);

}
