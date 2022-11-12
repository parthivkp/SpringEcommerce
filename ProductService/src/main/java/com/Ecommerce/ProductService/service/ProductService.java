package com.Ecommerce.ProductService.service;

import org.springframework.stereotype.Service;

import com.Ecommerce.ProductService.model.ProductRequest;
import com.Ecommerce.ProductService.model.ProductResponse;

@Service
public interface ProductService {

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);

}
