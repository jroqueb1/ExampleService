package com.globomantics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.globomantics.entity.Product;
import com.globomantics.service.ProductService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

	@MockBean
	private ProductService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("GET /product/1 - Found")
	void testGetProductByIdFound() throws Exception {
	
		//Setup out mocked service
//		Product mockProduct = new Product(1L, "Product Name", 10, 1);
		
	}
}
