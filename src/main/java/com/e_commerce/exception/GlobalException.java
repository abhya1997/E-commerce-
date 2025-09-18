package com.e_commerce.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(UserAllReadyPresentException.class)
	public ResponseEntity<Object> userallReadyPresentException(UserAllReadyPresentException presentException) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(presentException.getMessage());

	}

	@ExceptionHandler(UsernameNotFound.class)
	public ResponseEntity<Object> UserNotFound(UsernameNotFound exception) {

		return ResponseEntity.status(HttpStatus.IM_USED).body(exception.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> handleJpaValidation(ConstraintViolationException ex) {
		Map<String, String> errors = ex.getConstraintViolations().stream()
				.collect(Collectors.toMap(cv -> cv.getPropertyPath().toString(), ConstraintViolation::getMessage));
		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, String>> handleConstraintViolation(DataIntegrityViolationException ex) {
		Throwable cause = ex.getCause();
		String failedConstraint = null;

		if (cause instanceof org.hibernate.exception.ConstraintViolationException) {
			failedConstraint = ((org.hibernate.exception.ConstraintViolationException) cause).getConstraintName();
		}

		Map<String, String> error = new HashMap<>();
		if (failedConstraint != null) {
			switch (failedConstraint) {
			case "unique_email_constraint":
				error.put("email", "Email already exists");
				break;
			case "unique_username_constraint":
				error.put("username", "Username already exists");
				break;
			case "unique_mobile_no_constraint":
				error.put("mobileNo", "Mobile number already exists");
				break;
			default:
				error.put("error", "Data integrity violation");
			}
		} else {
			error.put("error", "Data integrity violation");
		}

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

}
