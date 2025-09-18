package com.e_commerce.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.e_commerce.entity.Cart;
import com.e_commerce.entity.CartItems;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
	@Query("SELECT ci FROM CartItems ci WHERE ci.cart.cartId = :cartId")
	List<CartItems> findCartItemsByCartId(@Param("cartId") Long cartId);

}
