package com.emazon.stock_api_service.infrastructure.output.mysql.entity;


import jakarta.persistence.Id;
import lombok.Data;


@Data
public class CategoryEntity {
    @Id
    private Long id;
    private String name;
    private String description;
}
