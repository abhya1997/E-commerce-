package com.e_commerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.e_commerce.entity.User;
import com.e_commerce.exception.UsernameNotFound;
import com.e_commerce.repo.UserRepo;
@Component
public class UserDetils implements UserDetailsService {
	@Autowired
	private UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username)  {
	    User user = repo.findByUsernameWithRoles(username).orElseThrow(()->new UsernameNotFound("User not Found"));
	    //System.out.println(user);
	    
	    return org.springframework.security.core.userdetails.User.builder()
	            .username(user.getUsername())
	            .password(user.getPassword()) // already hashed in DB
	            .authorities(user.getRoles().stream()
	                    .map(role -> new SimpleGrantedAuthority(role.getRole()))
	                    .toList())
	            .build();
	}

}
