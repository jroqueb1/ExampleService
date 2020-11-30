package com.globomantics.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.globomantics.entity.Product;
import com.globomantics.service.ProductService;

@RestController
public class ProductController {
//	private static final Logger logger = LogManager.getLogger(ProductController.class);
	
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/product/{id}") 
	public ResponseEntity<?> getProduct(@PathVariable Integer id) {
		return productService.findById(id)
				.map(product -> {
					try {
						return ResponseEntity.ok()
								.eTag(Integer.toString(product.getVersion()))
								.location(new URI("/product/" + product.getId()))
								.body(product);
					} catch(URISyntaxException e) {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					}
				}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/products")
	public Iterable<Product> getProducts() {
		return productService.findAll();
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product newProduct = productService.save(product);
		
		try {
			return ResponseEntity
					.created(new URI("/product/" + newProduct.getId()))
					.eTag(Integer.toString(newProduct.getVersion()))
					.body(newProduct);
		} catch(URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product,
			@PathVariable Integer id, @RequestHeader("If-Match") Integer ifMatch){
		
		Optional<Product> existingProduct = productService.findById(id);

		return existingProduct.map(p -> {
			if(!p.getVersion().equals(ifMatch)) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			
			p.setName(product.getName());
			p.setQuantity(product.getQuantity());
			p.setVersion(p.getVersion() + 1);
			
			try {
				if(productService.update(p)) {
					return ResponseEntity.ok()
							.location(new URI("/product/" + p.getId()))
							.eTag(Integer.toString(p.getVersion()))
							.body(p);
				} else {
					return ResponseEntity.notFound().build();
				}
			} catch(URISyntaxException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
