package com.example.PaymentService.service;

import com.example.PaymentService.entity.TransactionDetails;
import com.example.PaymentService.model.PaymentRequest;
import com.example.PaymentService.repository.TransactionDetailsRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
public class PaymentServiceImpl implements  PaymentService{

    @Autowired
        private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment details {}",paymentRequest);

        TransactionDetails transactionDetails
                = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().toString())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction Completed with id{}", transactionDetails.getId());

        return transactionDetails.getId();


    }

    @Override
    @RateLimiter(name="ratelimit",fallbackMethod = "d")
    public TransactionDetails getTransactionByorderId(long orderId) {
        log.info("Fetching Transaction with Order id "+orderId);
        TransactionDetails transactionDetails=transactionDetailsRepository.findByOrderId(orderId);
        log.info("Transaction Found {}", transactionDetails);
        return  transactionDetails;
    }
    public  TransactionDetails d(Exception e){

        TransactionDetails transactionDetails =TransactionDetails.builder()
                        .paymentMode("RATE LIMITER ENTERED").
                build();
        return
                transactionDetails;
    }


}
