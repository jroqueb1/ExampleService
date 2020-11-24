package com.globomantics.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
//	private static final Logger logger = LogManager.getLogger(ProductController.class);
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	public ResponseEntity<?> getProduct(@PathVariable Integer id) {
		return productService.findById(id)
				.map(product -> {
					try {
						return ResponseEntity.ok()
								.eTag(In)
					} catch(URISyntaxException e) {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					}
				}).orElse(ResponseEntity.notFound().build());
	}
	
}
