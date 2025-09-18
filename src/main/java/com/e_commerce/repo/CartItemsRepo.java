package com.e_commerce.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.e_commerce.entity.CartItems;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItems, Long>{
    //Optional<CartItems> findByProductsProductIdAndCartCartId(Long productsId, Long cartId);
	 
   //void deleteByCart_CartIdAndProducts_ProductsId(Long cartId, Long productsId);
	void deleteByCart_CartIdAndProducts_ProductsId(Long cartId, Long productsId);


}
