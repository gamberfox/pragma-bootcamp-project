package com.emazon.stock_api_service.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequest {
    private String name;
    private String description;
}
