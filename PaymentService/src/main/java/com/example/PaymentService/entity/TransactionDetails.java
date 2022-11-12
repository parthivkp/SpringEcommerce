package com.example.PaymentService.entity;

import com.example.PaymentService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="TRANSACTION_DETAILS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long orderId;
    private Instant paymentDate;
    private String referenceNumber;
    private String paymentStatus;
    private long amount;
    private String paymentMode;

}
