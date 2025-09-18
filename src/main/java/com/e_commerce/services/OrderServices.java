package com.e_commerce.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.entity.Cart;
import com.e_commerce.entity.CartItems;
import com.e_commerce.entity.OrderItem;
import com.e_commerce.entity.Orders;
import com.e_commerce.entity.Payment;
import com.e_commerce.entity.Products;
import com.e_commerce.entity.User;
import com.e_commerce.repo.CartItemsRepo;
import com.e_commerce.repo.CartRepo;
import com.e_commerce.repo.OrdersItemsRepo;
import com.e_commerce.repo.OrdersRepo;
import com.e_commerce.repo.PayementRepo;
import com.e_commerce.repo.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class OrderServices {

	@Autowired
	private OrdersRepo ordersRepo;

	@Autowired
	private OrdersItemsRepo ordersItemsRepo;

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private UserRepo repo;

	@Autowired
	private PayementRepo paymentRepo;
	
	@Autowired
	private CartItemsRepo cartItemsRepo;

	@Transactional
	public String orderPlaced(Long cartId, String email, String paymentMethod) {
		Orders orders = new Orders();
		Cart cart = cartRepo.findById(cartId).orElseThrow();
		User user = repo.findById(cart.getUser().getUserId()).orElseThrow();
		orders.setEmail(user.getEmail());
		orders.setOrderDate(LocalDate.now());
		orders.setTotalAmount(cart.getCartTotal());
		Orders saveOrders = ordersRepo.save(orders);
		/////////////////
		Payment payment = new Payment();
		payment.setOrder(orders);
		payment.setPaymentMethod(paymentMethod);

		payment = paymentRepo.save(payment);

		orders.setPayment(payment);

		Orders savedOrders = ordersRepo.save(orders);

		List<CartItems> list = cartRepo.findCartItemsByCartId(cartId);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (CartItems cartItem : list) {
			OrderItem orderItem = new OrderItem();

			orderItem.setProduct(cartItem.getProducts());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setDiscount(cartItem.getDiscount());
			orderItem.setOrderedProductPrice(cartItem.getProductPrice());
			orderItem.setOrder(saveOrders);
			orderItems.add(orderItem);

		}
		orderItems = ordersItemsRepo.saveAll(orderItems);

		ordersItemsRepo.saveAll(orderItems);
		
		list.forEach(
				(cartItems)->{
				
			int qunatity=cartItems.getQuantity();

			Products product = cartItems.getProducts();

		//	cartService.deleteProductFromCart(cartId, cartItems.getProducts().getProductsId());
         //cartRepo.deleteById(cartId);
         cartItemsRepo.deleteByCart_CartIdAndProducts_ProductsId(cartId, product.getProductsId());
         
			product.setQuantity(product.getQuantity() - qunatity);
			
			
				
		});
		
		
		return "Order place sucessfully";
	}

}
