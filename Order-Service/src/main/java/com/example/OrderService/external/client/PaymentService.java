package com.example.OrderService.external.client;


import com.example.OrderService.exception.QuantityErrorException;
import com.example.OrderService.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@CircuitBreaker(name="external" ,fallbackMethod="fallback3")
@RateLimiter(name="ratelimit",fallbackMethod = "d")
@FeignClient(name="PAYMENT-SERVICE/payment")
public interface PaymentService {

    @PostMapping("/doPayment")
    public long doPayment(@RequestBody PaymentRequest paymentRequest);

    default long fallback3(PaymentRequest p, Exception e){
        throw new QuantityErrorException("Payment SERVICE is unavailable", HttpStatus.INTERNAL_SERVER_ERROR);

    }
    default long d(Exception e){
        throw new QuantityErrorException("Payment SERVICE Rate Limiter 2", HttpStatus.TOO_MANY_REQUESTS);

    }

}
