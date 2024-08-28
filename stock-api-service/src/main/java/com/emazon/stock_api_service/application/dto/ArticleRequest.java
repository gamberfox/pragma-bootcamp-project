package com.emazon.stock_api_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ArticleRequest {
    private String name;
    private String description;
    private Long stock;
    private BigDecimal price;
    //@NotNull(message=BRAND_ID_OBLIGATORY)
    private Long brandId;
    //@NotNull(message=CATEGORY_IDS_OBLIGATORY)
    private List<Long> categoryIds;
}
