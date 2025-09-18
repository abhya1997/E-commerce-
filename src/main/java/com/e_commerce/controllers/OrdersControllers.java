package com.e_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.services.OrderServices;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = { "http://127.0.0.1:5500","null" }) 
public class OrdersControllers {

	@Autowired
	private OrderServices orderServices;
	
	
	@PostMapping("/placeOrder")
	public ResponseEntity<String>placeOrder(@RequestParam Long cartId,
		@RequestParam String email,@RequestParam String paymentMethod){
		
		return ResponseEntity.ok(orderServices.orderPlaced(cartId, email, paymentMethod));
	}
	
}
