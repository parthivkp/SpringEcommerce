package com.Ecommerce.ProductService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Ecommerce.ProductService.model.ErrorMessage;

@ControllerAdvice
public class RestResponseEntityException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NoRecordFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage productNotFound (NoRecordFoundException e) {
		ErrorMessage er=new ErrorMessage();
		er.setHttpStatus(e.getStatus());
		er.setMessage(e.getMessage());
		return er;
	}
	

}
