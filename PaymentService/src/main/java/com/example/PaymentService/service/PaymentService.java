package com.example.PaymentService.service;

import com.example.PaymentService.entity.TransactionDetails;
import com.example.PaymentService.model.PaymentRequest;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    public long doPayment(PaymentRequest paymentRequest);

    public TransactionDetails getTransactionByorderId(long orderId);
}
