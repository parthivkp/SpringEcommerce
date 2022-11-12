package com.example.OrderService.exception;

import com.example.OrderService.external.client.decoder.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class QuantityErrorExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(QuantityErrorException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage productNotFound (QuantityErrorException e) {
		ErrorMessage er=new ErrorMessage();
		er.setHttpStatus(e.getStatus());
		er.setMessage(e.getMessage());
		return er;
	}
	

}
