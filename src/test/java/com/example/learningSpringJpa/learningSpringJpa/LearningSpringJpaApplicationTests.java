package com.example.learningSpringJpa.learningSpringJpa;

import com.example.learningSpringJpa.learningSpringJpa.entities.Product;
import com.example.learningSpringJpa.learningSpringJpa.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class LearningSpringJpaApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository(){

		Product productEntity = Product.builder()
				.sku("nestle1245")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(123.45))
				.quantity(5)
				.build();

		Product savedProduct = productRepository.save(productEntity);

		System.out.println(savedProduct);
	}

	@Test
	void getRepositories(){
		List<Product> products = productRepository.findByQuantityGreaterThanAndPriceLessThan( 3 , BigDecimal.valueOf(15.0));
		System.out.println(products);
	}

}
