package com.e_commerce.controllers;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.dto.UserDto;
import com.e_commerce.entity.User;
import com.e_commerce.jwt.JwtUtil;
import com.e_commerce.repo.UserRepo;
import com.e_commerce.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://127.0.0.1:5500","null" }) 
public class UserControllers {

	private Logger logger = LoggerFactory.getLogger(UserControllers.class);

	@Autowired
	private UserServices services;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping(value = "/registration", consumes = "application/json")
	public ResponseEntity<?> regisister(@RequestBody User user, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, String> errors = result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			return ResponseEntity.badRequest().body(errors);
		}
		services.registerUser(user);
		// proceed to save user
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserDto authRequest) {
	    logger.debug("Login attempt for user: {}", authRequest.getUsername());

	    try {
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String token = jwtUtil.generateToken(authRequest.getUsername());

	        return ResponseEntity.ok()
	            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
	            .body(Map.of("token", token));
	    } catch (BadCredentialsException ex) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(Map.of("error", "Invalid credentials"));
	    }
	}

	@GetMapping("/get")
	public ResponseEntity<String> getResponse() {
		return ResponseEntity.ok("it fine");
	}

}
