package com.vikas.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vikas.util.ApiErrorResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Class : GlobalExceptionHandler Description: [Add brief description here]
 * Author : Vikas Yadav Created On : Feb 17, 2026 Version : 1.0
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * Handle Custom Business Exception
	 */
	@ExceptionHandler(RentHubServiceException.class)
	public ResponseEntity<ApiErrorResponse> handleRentHubServiceException(RentHubServiceException ex,
			ServerHttpRequest request) {

		ApiErrorResponse errorResponse = new ApiErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setPath(request.getURI().getPath());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle Generic Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleGlobalException(Exception ex, ServerHttpRequest request) {

		ApiErrorResponse errorResponse = new ApiErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setMessage("Something went wrong");
		errorResponse.setPath(request.getURI().getPath());

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}