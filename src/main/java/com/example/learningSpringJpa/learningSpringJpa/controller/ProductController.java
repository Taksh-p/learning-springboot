package com.example.learningSpringJpa.learningSpringJpa.controller;

import com.example.learningSpringJpa.learningSpringJpa.entities.Product;
import com.example.learningSpringJpa.learningSpringJpa.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    ProductRepository productRepository;
    private final int PAGE_SIZE = 5;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProduct(@RequestParam(defaultValue = "id") String sortBy,
                                       @RequestParam(defaultValue = "0") Integer page){
//        return productRepository.findAll(
//                Sort.by(Sort.Direction.DESC, sortBy, "quantity", "price")
//        );

        Pageable pageable = PageRequest.of(page,PAGE_SIZE);

        return productRepository.findAll(pageable).getContent();
//        return  productRepository.findAll(
//                Sort.by(
//                        Sort.Order.desc("quantity"),
//                        Sort.Order.desc("title")
//                )
//        );


    }

    @GetMapping(path = "/{productId}")
    public Product getProductById(@PathVariable Long productId){

      return productRepository.findById(productId).orElse(null);
    }

}
