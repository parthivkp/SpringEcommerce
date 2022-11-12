package com.example.OrderService.model;

import com.example.OrderService.request.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {


	private long productId;
	private long totalAmount;
	private long quantity;
	private PaymentMode paymentMode;
}
