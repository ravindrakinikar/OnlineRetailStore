package com.mo.assemnt.online_retail_store.controller;

import java.net.URI;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mo.assemnt.online_retail_store.controller.product.beans.ProductInfo;
import com.mo.assemnt.online_retail_store.dataaccess.entity.Product;
import com.mo.assemnt.online_retail_store.service.ProductService;

@RestController
public class ProductController {

	final Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductInfo productInfo) {
		logger.info("Input recieved to create product = " + productInfo);
		Product product = productService.createProduct(productInfo);
		logger.info("Created product with id = " + product.getId());
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();
		logger.info("Setting header url with newly created product= " + product.getId());
		return ResponseEntity.created(newPollUri).build();
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("{\"status\": \"success\"}", HttpStatus.OK);
	}

	@GetMapping("/products")
	public ResponseEntity<Iterable<Product>> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {

		Product product = productService.getProductById(id);
		if (product == null)
			throw new CustomException("Product Not Found with Id " + id);

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductInfo productInfo, @PathVariable Long id) {
		Product prod = productService.updateProduct(productInfo, id);
		logger.info("updated product id = " + prod.getId());
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}

}
