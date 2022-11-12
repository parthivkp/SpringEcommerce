package com.Ecommerce.ProductService.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {
private String message;
private HttpStatus httpStatus;
public ErrorMessage(String m,HttpStatus s) {
	message=m;
	httpStatus=s;
}

}
