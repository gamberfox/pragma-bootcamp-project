package com.emazon.stock_api_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class    Article {
    private Long id;
    private String name;
    private String description;
    private Long stock;
    private BigDecimal price;
    private Long brandId;
}
