package com.spring.ecommerce.controller;

import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/regular")
    public ResponseEntity<List<Product>> getRegularDeals() {
        return ResponseEntity.ok(productRepository.findByOnDealTrue());
    }

    @GetMapping("/midnight")
    public ResponseEntity<List<Product>> getMidnightDeals() {
        LocalDateTime now = LocalDateTime.now();
        List<Product> midnightDeals = productRepository.findByOnMidnightDealTrueAndMidnightDealStartBeforeAndMidnightDealEndAfter(now, now);
        return ResponseEntity.ok(midnightDeals);
    }
}
