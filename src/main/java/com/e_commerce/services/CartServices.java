package com.e_commerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.entity.Cart;
import com.e_commerce.entity.CartItems;
import com.e_commerce.entity.Products;
import com.e_commerce.exception.APIException;
import com.e_commerce.exception.ItemsAlreadyIntheCart;
import com.e_commerce.exception.NotCartFoundException;
import com.e_commerce.exception.ProductNotFoundException;
import com.e_commerce.repo.CartItemsRepo;
import com.e_commerce.repo.CartRepo;
import com.e_commerce.repo.CategoriesRepo;
import com.e_commerce.repo.ProductsRepo;

import jakarta.transaction.Transactional;

@Service
public class CartServices {
	
	@Autowired
	private CategoriesRepo categoriesRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ProductsRepo productsRepo;
	
	@Autowired
	private CartItemsRepo cartItemsRepo;
	
	
	@Transactional
	public Cart addProducts(Long cartId,Long productId,int quantity) throws APIException {
		Cart cart=cartRepo.findById(cartId).orElseThrow(()-> new NotCartFoundException("Cart is not For this User"));
		Products products=productsRepo.findById(productId).orElseThrow(()->new ProductNotFoundException("product not found this time"));
		
		CartItems cartItems=null;//cartItemsRepo.findByProductsProductIdAndCartCartId(productId, cartId).orElseThrow(()->new ItemsAlreadyIntheCart("Items already in the Cart"));
		if (products.getQuantity() == 0) {
			throw new APIException(products.getProduct_name() + " is not available");
		}

		if (products.getQuantity() < quantity) {
			throw new APIException("Please, make an order of the " + products.getProduct_name()
					+ " less than or equal to the quantity " + products.getQuantity() + ".");
		}

		CartItems newCartItem = new CartItems();

		newCartItem.setProducts(products);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(quantity);
		newCartItem.setDiscount(products.getDiscount());
		newCartItem.setProductPrice(products.getSpecialPrice());

		cartItemsRepo.save(newCartItem);
		
		products.setQuantity(products.getQuantity() - quantity);

		cart.setCartTotal(cart.getCartTotal() + (products.getSpecialPrice() * quantity));
		List<CartItems>list=cart.getProductList();
		cart.setProductList(list);
		return cart;
	}

}
