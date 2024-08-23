package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.BrandRequest;
import com.emazon.stock_api_service.application.dto.BrandResponse;

import java.util.List;

public interface IBrandHandler {
    void createBrand(BrandRequest brandRequest);
    BrandResponse getBrandById(Long id);
    BrandResponse getBrandByName(String name);
}
