package com.spring.ecommerce.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

    // For deals
    private boolean onDeal;
    private BigDecimal dealPrice;

    // For midnight deals
    private boolean onMidnightDeal;
    private LocalDateTime midnightDealStart;
    private LocalDateTime midnightDealEnd;
}
