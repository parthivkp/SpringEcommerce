package com.example.OrderService.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {


    private long id;
    private long productId;
    private long quantity;
    private Instant orderDate;
    private String orderStatus;
    public ProductDetail productDetail;
    public TransactionDetails transactionDetails;
    private long amount;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductDetail {
        private String productName;
        private long productId;
        private long quantity;
        private long price;

    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionDetails {
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
}
