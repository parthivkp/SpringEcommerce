package com.example.OrderService.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class QuantityErrorException extends RuntimeException {
	HttpStatus status;
	public QuantityErrorException(String message, HttpStatus s) {
		super(message);
		status=s;
	}

}
