package com.book.catalogue.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.book.catalogue.exception.ErrorResponse;
 
/**
 * Generic exception handler
 * 
 * @author Akila
 *
 */
@ControllerAdvice
public class ExceptionController {
 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Generic exception has occured - Please verify the logs");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}