package com.example.PaymentService.controller;

import com.example.PaymentService.entity.TransactionDetails;
import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.service.PaymentService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Log4j2
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        log.info("payment controller {}",paymentRequest);

        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK
        );


    }



    @GetMapping("/get/{orderId}")
    public ResponseEntity<TransactionDetails> getTransactionByOrderId(@PathVariable long orderId){
        return new ResponseEntity<>(
                paymentService.getTransactionByorderId(orderId),
                HttpStatus.OK
        );
    }


}
