package com.example.OrderService.external.client;

import com.example.OrderService.exception.QuantityErrorException;
import com.example.OrderService.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name="external" ,fallbackMethod="fallback2")
@FeignClient(name="PRODUCT-SERVICE")
public interface ProductService {
    @PutMapping("/product/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);

    default ResponseEntity<Void> fallback2(Exception e){
        throw new QuantityErrorException("product SERVICE is unavailable", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
