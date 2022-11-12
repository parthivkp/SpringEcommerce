package com.example.OrderService.controller;


import com.example.OrderService.entity.OrderEntity;
import com.example.OrderService.model.OrderResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderContoller {
	@Autowired
	private OrderService orderService;


	@PostMapping("/placeOrder")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
		long orderId=orderService.placeOrder(orderRequest);
		log.info("Order Id:{}",orderId);
		return new ResponseEntity<>(orderId,HttpStatus.OK);
	}

	@GetMapping("/getorderdetails/{orderId}")
	public OrderResponse getOrderDetails(@PathVariable long orderId){
		OrderResponse orderResponse=orderService.getOrderDetail(orderId);
		return orderResponse;
	}
	

}
