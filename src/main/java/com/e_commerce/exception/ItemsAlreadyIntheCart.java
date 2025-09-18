package com.e_commerce.exception;

public class ItemsAlreadyIntheCart extends RuntimeException {

	public ItemsAlreadyIntheCart(String msg) {
		super(msg);
	}
}
