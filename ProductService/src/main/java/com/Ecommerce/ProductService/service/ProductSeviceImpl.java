package com.Ecommerce.ProductService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Ecommerce.ProductService.entity.Product;
import com.Ecommerce.ProductService.exceptions.NoRecordFoundException;
import com.Ecommerce.ProductService.model.ProductRequest;
import com.Ecommerce.ProductService.model.ProductResponse;
import com.Ecommerce.ProductService.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductSeviceImpl implements ProductService {

	
		
	@Autowired
	private ProductRepository productRepository;
	@Override
	public long addProduct(ProductRequest productRequest) {
		log.info("adding product");
		Product product=Product.builder()
				.productName(productRequest.getName())
				.quantity(productRequest.getQuantity())
				.price(productRequest.getPrice())
				.build();
		productRepository.save(product);
		log.info("Product Created");
		
		return product.getProductId();
	}
	@Override
	public ProductResponse getProductById(long productId) {
		Product product=productRepository.findById(productId).orElseThrow(()-> new NoRecordFoundException("Product with produt id ->"+ productId+ "<- not found in db",HttpStatus.NOT_FOUND));
		ProductResponse productResponse=ProductResponse.builder()
				.productName(product.getProductName())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.build();
		// or BeanUtils.copyProperties(product,productResponse);
		return productResponse;
	}
	@Override
	public void reduceQuantity(long productId, long quantity) {
		log.info("Reduce quantity {} for id {}",quantity,productId);
		Product product=productRepository.findById(productId).orElseThrow(()-> new NoRecordFoundException("Product with productid "+productId+" Not found in db",HttpStatus.NOT_FOUND));
		if(product.getQuantity()<quantity) {
			throw new NoRecordFoundException("Product does not have sufficient Quantity. Available is->"+product.getQuantity()+"But requested is "+quantity,HttpStatus.INSUFFICIENT_STORAGE);
		}
		product.setQuantity(product.getQuantity() -quantity);
		productRepository.save(product);
		log.info("Product Quantity Updated Successfully");
	}
	

}
