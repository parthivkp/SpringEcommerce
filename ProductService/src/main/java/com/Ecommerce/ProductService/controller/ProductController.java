package com.Ecommerce.ProductService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.ProductService.model.ProductRequest;
import com.Ecommerce.ProductService.model.ProductResponse;
import com.Ecommerce.ProductService.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
		
		@Autowired
		private ProductService productService;
		
		@PostMapping
		public ResponseEntity<Long> addproduct(@RequestBody ProductRequest productRequest){
			long productId=productService.addProduct(productRequest);
			return new ResponseEntity<>(productId,HttpStatus.CREATED);
		}
		@GetMapping("/{productId}")
		@ResponseBody
		public ResponseEntity<ProductResponse> getProductById(@PathVariable  long productId){
			ProductResponse productResponse=productService.getProductById(productId);
			return new ResponseEntity<>(productResponse,HttpStatus.ACCEPTED);
		}
		@PutMapping("/reduceQuantity/{id}")
		public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity){
			productService.reduceQuantity(productId,quantity);
			return new ResponseEntity<>(HttpStatus.OK);
		}
}
