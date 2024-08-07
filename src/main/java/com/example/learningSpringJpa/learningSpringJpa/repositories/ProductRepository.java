package com.example.learningSpringJpa.learningSpringJpa.repositories;

import com.example.learningSpringJpa.learningSpringJpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCreatedAtAfter(LocalDateTime localDateTime);

    List<Product> findByQuantityAndPrice(int i, BigDecimal bigDecimal);

    List<Product> findByQuantityGreaterThanAndPriceLessThan(int i, BigDecimal bigDecimal);

    List<Product> findByQuantityGreaterThanOrPriceLessThan(int i, BigDecimal bigDecimal);
}
