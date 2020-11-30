package com.globomantics.service;

import java.util.List;
import java.util.Optional;

import com.globomantics.entity.Product;

public interface ProductService {

	List<Product> findAll();
	Optional<Product> findById(Integer id);
	Product save(Product product);
	boolean update(Product product);
}
