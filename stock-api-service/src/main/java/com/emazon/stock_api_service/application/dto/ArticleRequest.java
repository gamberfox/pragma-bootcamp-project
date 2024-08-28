package com.emazon.stock_api_service.application.dto;

import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

import static com.emazon.stock_api_service.util.BrandConstants.BRAND_ID_OBLIGATORY;

@Getter
@Setter
public class ArticleRequest {
    private String name;
    private String description;
    private Long stock;
    private BigDecimal price;
    @NotNull(message=BRAND_ID_OBLIGATORY)
    private Long brandId;
    private List<Long> categoryIds;
}
