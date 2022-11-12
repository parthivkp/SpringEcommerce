package com.Ecommerce.ProductService.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class NoRecordFoundException extends RuntimeException {
	HttpStatus status;
	public NoRecordFoundException(String message,HttpStatus s) {
		super(message);
		status=s;
	}

}
