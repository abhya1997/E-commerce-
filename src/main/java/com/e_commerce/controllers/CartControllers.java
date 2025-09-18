package com.e_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.entity.Cart;
import com.e_commerce.exception.APIException;
import com.e_commerce.services.CartServices;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = { "http://127.0.0.1:5500","null" }) 
public class CartControllers {

	@Autowired
	private CartServices cartServices;
	
	
	@GetMapping("/addItems/{cd}/{pd}/{qn}")
	public ResponseEntity<Cart>addProducts(@PathVariable Long cd,@PathVariable Long pd,@PathVariable int qn) throws APIException{
		return ResponseEntity.ok(cartServices.addProducts(cd,pd,qn));
				
		
	}
	
}
