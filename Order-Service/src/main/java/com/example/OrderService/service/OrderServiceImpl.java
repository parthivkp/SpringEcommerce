package com.example.OrderService.service;

import java.time.Instant;

import com.example.OrderService.exception.QuantityErrorException;
import com.example.OrderService.external.client.PaymentService;
import com.example.OrderService.external.client.ProductService;
import com.example.OrderService.model.OrderResponse;
import com.example.OrderService.request.PaymentRequest;
import com.example.OrderService.request.ProductResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.apache.commons.lang.ObjectUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.OrderService.entity.OrderEntity;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import static com.example.OrderService.model.OrderResponse.*;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public long placeOrder(OrderRequest orderRequest) {
		// order Entity->save the data with status Order Created
		// product Service check for stock and reduce
		// payment  Service ->success or cancelled
		
		log.info("Placing Order request {}",orderRequest);
		productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
		log.info("Created order");
		OrderEntity order=OrderEntity.builder()
				.amount(orderRequest.getTotalAmount())
				.quantity(orderRequest.getQuantity())
				.orderDate(Instant.now())
				.orderStatus("CREATED")
				.productId(orderRequest.getProductId())
				.build();
		order=orderRepository.save(order);

		log.info("Calling Payment Service");
		PaymentRequest paymentRequest=PaymentRequest.builder()
						.orderId(order.getId())
								.paymentMode(orderRequest.getPaymentMode())
										.amount(orderRequest.getTotalAmount())
												.build();
		log.info("Placing payment request {}",paymentRequest);
		String orderStatus="placed";
		long id=0;
		id=paymentService.doPayment(paymentRequest);

		order.setOrderStatus(orderStatus);

		orderRepository.save(order);

		log.info("Order Placed success with OrderId {}",order.getId());
		return order.getId();
	
	}
	//@RateLimiter(name="ratelimit",fallbackMethod = "d")
	@Override
	public OrderResponse getOrderDetail(long orderId) {
		OrderEntity orderEntity= orderRepository.findById(orderId).orElseThrow(()->new QuantityErrorException("Order with given id not found", HttpStatus.NOT_FOUND));
		//RestTemplate restTemplate=new RestTemplate();
		OrderResponse.ProductDetail productDetails1=
				 restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+orderEntity.getProductId(),OrderResponse.ProductDetail.class);
		OrderResponse.TransactionDetails transactionDetails1=
				restTemplate.getForObject("http://PAYMENT-SERVICE/payment/get/"+orderEntity.getId(),OrderResponse.TransactionDetails.class);
		
		OrderResponse orderResponse= builder()
				.amount(orderEntity.getAmount())
				.id(orderEntity.getId())
				.orderDate(orderEntity.getOrderDate())
				.orderStatus(orderEntity.getOrderStatus())
				.quantity(orderEntity.getQuantity())
				.productId(orderEntity.getProductId())
				.productDetail(productDetails1)
				.transactionDetails(transactionDetails1)
				.build();
		return orderResponse;

	}
	public OrderResponse d(Exception e){
		throw new QuantityErrorException("Payment SERVICE Rate Limiter ", HttpStatus.TOO_MANY_REQUESTS);

	}



}
