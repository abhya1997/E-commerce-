package com.e_commerce.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.e_commerce.entity.Cart;
import com.e_commerce.entity.Role;
import com.e_commerce.entity.User;
import com.e_commerce.exception.UserAllReadyPresentException;
import com.e_commerce.repo.UserRepo;

@Service
public class UserServices {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public User registerUser(User user) {
		userRepo.findUserByusername(user.getUsername())
        .ifPresent(existingUser -> {
            throw new UserAllReadyPresentException("User Already Present");
        });
		Role role=new Role();
		role.setRole("customer");
		role.setUser(user);
	
		user.setPassword(encoder.encode(user.getPassword()));
		user.getListAddresses().forEach(address -> address.setUser(user));
		//user.getRoles().forEach(roles->roles.setUser(user));
		Cart cart=new  Cart();
		cart.setUser(user);
		user.setCart(cart);

		return userRepo.save(user);
		
		
	}

}
