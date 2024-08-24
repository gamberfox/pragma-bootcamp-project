package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.BrandRequest;
import com.emazon.stock_api_service.application.dto.BrandResponse;

public interface IBrandHandler {
    void createBrand(BrandRequest brandRequest);
    BrandResponse getBrandResponseById(Long id);
    BrandResponse getBrandResponseByName(String name);
}
